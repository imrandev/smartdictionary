package com.nerd.app.voisy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 4/21/2017.
 */

public class GramaticalExample {

    @SerializedName("examples")
    @Expose
    private List<Example_> examples = null;
    @SerializedName("pattern")
    @Expose
    private String pattern;

    /**
     * No args constructor for use in serialization
     */
    public GramaticalExample() {
    }

    /**
     * @param pattern
     * @param examples
     */
    public GramaticalExample(List<Example_> examples, String pattern) {
        super();
        this.examples = examples;
        this.pattern = pattern;
    }

    public List<Example_> getExamples() {
        return examples;
    }

    public void setExamples(List<Example_> examples) {
        this.examples = examples;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
