package net.radmag.model;

import java.util.List;

/**
 * Created by Stephen on 6/13/2017.
 */
public class NameDocument {
    private List<String> titles;
    private List<String> prefixes;
    private List<String> postfixes;
    private List<String> items;

    public NameDocument() {
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
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

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}