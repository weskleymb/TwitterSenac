package br.senac.rn.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.senac.rn.model.Tweet;

public class TweetEdit extends Activity {

    private TextView tvLogin, tvData;
    private EditText etTweet;
    private Button btSalvar, btVoltar;
    private Tweet tweet;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_edit);
        setComponents();
        setEvents();
    }

    private void setComponents() {
        intent = getIntent();
        tweet = (Tweet) intent.getSerializableExtra("tweet");

        tvLogin = (TextView) findViewById(R.id.edit_autor);
        tvLogin.setText(tweet.getAutor().getLogin());

        tvData = (TextView) findViewById(R.id.edit_data);
        tvData.setText(tweet.getDataFormatada());

        etTweet = (EditText) findViewById(R.id.edit_tweet);
        etTweet.setText(tweet.getTweet());

        btSalvar = (Button) findViewById(R.id.edit_salvar);
        btVoltar = (Button) findViewById(R.id.edit_voltar);
    }

    private void setEvents() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void salvar() {
        tweet.setTweet(etTweet.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("tweet", tweet);
        setResult(RESULT_OK, intent);
        onBackPressed();
    }

}
