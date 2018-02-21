package com.nerd.app.voisy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 4/21/2017.
 */

public class Example__ {

    @SerializedName("text")
    @Expose
    private String text;

    /**
     * No args constructor for use in serialization
     */
    public Example__() {
    }

    /**
     * @param text
     */
    public Example__(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}