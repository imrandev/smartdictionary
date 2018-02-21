package com.nerd.app.voisy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 4/21/2017.
 */

public class Pronunciation {

    @SerializedName("ipa")
    @Expose
    private String ipa;

    /**
     * No args constructor for use in serialization
     */
    public Pronunciation() {
    }

    /**
     * @param ipa
     */
    public Pronunciation(String ipa) {
        super();
        this.ipa = ipa;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

}