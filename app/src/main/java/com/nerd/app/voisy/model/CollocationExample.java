package com.nerd.app.voisy.model;

/**
 * Created by hp on 5/10/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollocationExample {

    @SerializedName("collocation")
    @Expose
    private String collocation;
    @SerializedName("example")
    @Expose
    private Example example;

    public String getCollocation() {
        return collocation;
    }

    public void setCollocation(String collocation) {
        this.collocation = collocation;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

}
