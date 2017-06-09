package net.radmag.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 6/9/2017.
 */
@Entity
public class NamePart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;
    @ElementCollection
    private List<String> tags;

    public NamePart() {
        this.tags = new ArrayList<String>();
    }

    public NamePart(String word, List<String> tags) {
        this.word = word;
        this.tags = tags;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getWord() {
        return this.word;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

}
