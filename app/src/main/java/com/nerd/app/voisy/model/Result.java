package com.nerd.app.voisy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 4/21/2017.
 */

public class Result {

    @SerializedName("datasets")
    @Expose
    private List<String> datasets = null;
    @SerializedName("headword")
    @Expose
    private String headword;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("part_of_speech")
    @Expose
    private String partOfSpeech;
    @SerializedName("pronunciations")
    @Expose
    private List<Pronunciation> pronunciations = null;
    @SerializedName("senses")
    @Expose
    private List<Sense> senses = null;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param id
     * @param pronunciations
     * @param datasets
     * @param headword
     * @param url
     * @param senses
     * @param partOfSpeech
     */
    public Result(List<String> datasets, String headword, String id, String partOfSpeech, List<Pronunciation> pronunciations, List<Sense> senses, String url) {
        super();
        this.datasets = datasets;
        this.headword = headword;
        this.id = id;
        this.partOfSpeech = partOfSpeech;
        this.pronunciations = pronunciations;
        this.senses = senses;
        this.url = url;
    }

    public List<String> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<String> datasets) {
        this.datasets = datasets;
    }

    public String getHeadword() {
        return headword;
    }

    public void setHeadword(String headword) {
        this.headword = headword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<Sense> getSenses() {
        return senses;
    }

    public void setSenses(List<Sense> senses) {
        this.senses = senses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
