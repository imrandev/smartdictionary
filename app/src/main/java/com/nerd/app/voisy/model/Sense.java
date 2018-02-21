package com.nerd.app.voisy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 4/21/2017.
 */

public class Sense {

    @SerializedName("collocation_examples")
    @Expose
    private List<CollocationExample> collocationExamples = null;
    @SerializedName("definition")
    @Expose
    private String [] definition = null;
    @SerializedName("gramatical_examples")
    @Expose
    private List<GramaticalExample> gramaticalExamples = null;
    @SerializedName("examples")
    @Expose
    private List<Example__> examples = null;
    @SerializedName("gramatical_info")
    @Expose
    private GramaticalInfo gramaticalInfo;
    @SerializedName("signpost")
    @Expose
    private String signpost;

    /**
     * No args constructor for use in serialization
     */
    public Sense() {
    }

    /**
     * @param gramaticalExamples
     * @param definition
     * @param signpost
     * @param examples
     */
    public Sense(String[] definition, List<GramaticalExample> gramaticalExamples, List<Example__> examples, String signpost) {
        super();
        this.definition = definition;
        this.gramaticalExamples = gramaticalExamples;
        this.examples = examples;
        this.signpost = signpost;
    }

    public List<CollocationExample> getCollocationExamples() {
        return collocationExamples;
    }

    public void setCollocationExamples(List<CollocationExample> collocationExamples) {
        this.collocationExamples = collocationExamples;
    }


    public String[] getDefinition() {
        return definition;
    }

    public void setDefinition(String[] definition) {
        this.definition = definition;
    }

    public List<GramaticalExample> getGramaticalExamples() {
        return gramaticalExamples;
    }

    public void setGramaticalExamples(List<GramaticalExample> gramaticalExamples) {
        this.gramaticalExamples = gramaticalExamples;
    }

    public List<Example__> getExamples() {
        return examples;
    }

    public void setExamples(List<Example__> examples) {
        this.examples = examples;
    }

    public GramaticalInfo getGramaticalInfo() {
        return gramaticalInfo;
    }

    public void setGramaticalInfo(GramaticalInfo gramaticalInfo) {
        this.gramaticalInfo = gramaticalInfo;
    }

    public String getSignpost() {
        return signpost;
    }

    public void setSignpost(String signpost) {
        this.signpost = signpost;
    }
}
