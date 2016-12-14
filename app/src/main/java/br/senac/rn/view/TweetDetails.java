package br.senac.rn.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.senac.rn.model.Tweet;

public class TweetDetails extends Activity {

    private TextView tvLogin, tvTweet, tvData;
    private Button btVoltar;
    private Intent intent;
    private Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_details);
        setComponents();
        setEvents();
    }

    private void setComponents() {
        intent = getIntent();
        tweet = (Tweet) intent.getSerializableExtra("tweet");

        tvLogin = (TextView) findViewById(R.id.login);
        tvLogin.setText(tweet.getAutor().getLogin());

        tvTweet = (TextView) findViewById(R.id.tweet);
        tvTweet.setText(tweet.getTweet());

        tvData = (TextView) findViewById(R.id.data);
        tvData.setText(tweet.getDataFormatada());

        btVoltar = (Button) findViewById(R.id.voltar);
    }

    private void setEvents() {
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telefonar();
            }
        });
    }

    private void telefonar() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tweet.getAutor().getFone()));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
            return;
        }

        startActivity(intent);
    }

}
