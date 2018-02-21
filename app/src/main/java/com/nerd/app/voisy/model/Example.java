package com.nerd.app.voisy.model;

/**
 * Created by hp on 5/10/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("audio")
    @Expose
    private List<Audio> audio = null;
    @SerializedName("text")
    @Expose
    private String text;

    public List<Audio> getAudio() {
        return audio;
    }

    public void setAudio(List<Audio> audio) {
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
