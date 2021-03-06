package br.senac.rn.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Tweet implements Serializable, Comparable<Tweet> {

    private Autor autor;
    private String tweet;
    private Date data;

    public Tweet() {
        this.data = new Date();
    }

    public Tweet(Autor autor, String tweet) {
        this.autor = autor;
        this.tweet = tweet;
        this.data = new Date();
    }

    public Autor getAutor() {
        return autor;
    }

    public String getTweet() {
        return tweet;
    }

    public Date getData() {
        return data;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataFormatada() {
        return DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR")).format(this.data);
    }

    @Override
    public int compareTo(Tweet tweet) {
        if (this.data.after(tweet.getData())) {
            return -1;
        } else if (this.data.before(tweet.getData())) {
            return +1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return  data.equals(((Tweet) o).data);
    }

}
