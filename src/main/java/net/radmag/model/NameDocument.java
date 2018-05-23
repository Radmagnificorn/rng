package net.radmag.model;

import java.util.List;

/**
 * Created by Stephen on 6/13/2017.
 */
public class NameDocument {
    private List<String> feature_descriptions;
    private List<String> prefixes;
    private List<String> postfixes;
    private List<String> features;

    public NameDocument() {
    }

    public NameDocument(List<String> feature_descriptions, List<String> prefixes, List<String> postfixes, List<String> features) {
        this.feature_descriptions = feature_descriptions;
        this.prefixes = prefixes;
        this.postfixes = postfixes;
        this.features = features;
    }

    public List<String> getFeature_descriptions() {
        return feature_descriptions;
    }

    public void setFeature_descriptions(List<String> feature_descriptions) {
        this.feature_descriptions = feature_descriptions;
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    public List<String> getPostfixes() {
        return postfixes;
    }

    public void setPostfixes(List<String> postfixes) {
        this.postfixes = postfixes;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}