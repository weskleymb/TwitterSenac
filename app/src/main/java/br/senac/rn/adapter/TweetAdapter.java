package br.senac.rn.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.senac.rn.model.Tweet;
import br.senac.rn.view.R;

public class TweetAdapter extends BaseAdapter {

    private Context context;
    private List<Tweet> tweets;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Tweet getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Tweet tweet = tweets.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tweet_layout, null);
        }

        TextView tvLogin = (TextView) view.findViewById(R.id.tvLogin);
        tvLogin.setText(tweet.getAutor().getLogin());

        TextView tvTweet = (TextView) view.findViewById(R.id.tvTweet);
        tvTweet.setText(tweet.getTweet());

        TextView tvData = (TextView) view.findViewById(R.id.tvData);
        tvData.setText(tweet.getDataFormatada());

        return view;
    }
}
