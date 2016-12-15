package br.senac.rn.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.senac.rn.adapter.TweetAdapter;
import br.senac.rn.model.Autor;
import br.senac.rn.model.Tweet;

public class TwitterActivity extends Activity {

    private EditText etTweet;
    private Button btTweetar;
    private ListView lvTweets;
    private ImageView ivFoto;
    private TweetAdapter adapter;
    private List<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setComponents();
        setEvents();
    }

    private void setComponents() {
        etTweet = (EditText) findViewById(R.id.etTweet);
        btTweetar = (Button) findViewById(R.id.btTweetar);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        tweets = new ArrayList();
        adapter = new TweetAdapter(this, tweets);
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        lvTweets.setAdapter(adapter);
    }

    private void setEvents() {
        btTweetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tweetar();
            }
        });
        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
        lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Tweet tweet = tweets.get(position);
                Intent intent = new Intent("br.senac.rn.DETALHES");
                intent.putExtra("tweet", tweet);
                startActivity(intent);
            }
        });
        lvTweets.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Tweet tweet = tweets.get(position);
                Intent intent = new Intent(TwitterActivity.this, TweetEdit.class);
                intent.putExtra("tweet", tweet);
                startActivityForResult(intent, 2);
                return true;
            }
        });
    }

    private void tweetar() {
        Autor autor = new Autor("@weskleymb", "weskleymb@hotmail.com", "996753679");
        Tweet tweet = new Tweet(autor, etTweet.getText().toString());
        tweets.add(tweet);
        Collections.sort(tweets);
        adapter.notifyDataSetChanged();
        limpar();
    }

    private void limpar() {
        etTweet.setText("");
        etTweet.requestFocus();
    }

    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    private void alterarFoto(Intent intent) {
        Bitmap foto = (Bitmap) intent.getExtras().get("data");
        ivFoto.setImageBitmap(foto);
    }

    @Override
    protected void onActivityResult(int request, int result, Intent intent) {
        if (request == 1 && result == RESULT_OK) {
            alterarFoto(intent);
        } else if (request == 2 && result == RESULT_OK) {
            Tweet tweet = (Tweet) intent.getSerializableExtra("tweet");
            int posicao = tweets.indexOf(tweet);
            tweets.set(posicao, tweet);
            adapter.notifyDataSetChanged();
        }
    }

}
