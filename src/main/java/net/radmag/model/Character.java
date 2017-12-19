package net.radmag.model;

public class Character {
    private String name;
    private String keyFeature;

    public Character(String name, String keyFeature) {
        this.name = name;
        this.keyFeature = keyFeature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyFeature() {
        return keyFeature;
    }

    public void setKeyFeature(String keyFeature) {
        this.keyFeature = keyFeature;
    }
}
