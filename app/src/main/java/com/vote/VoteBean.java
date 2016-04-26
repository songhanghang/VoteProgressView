package com.vote;

/**
 * Created by songhang on 16/4/26.
 */
public class VoteBean {
    public String title;
    public int progress;
    public int color;

    public VoteBean(String title, int progress, int color) {
        this.title = title;
        this.progress = progress;
        this.color = color;
    }
}
