package net.radmag.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;
    private String type;

    protected Word() {}

    public Word(String word, String type) {
        this.word = word;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Word[id=%d, word=%s, type=%s]", id, word, type);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
