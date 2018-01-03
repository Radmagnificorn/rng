package net.radmag.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.radmag.model.Character;
import net.radmag.model.NameDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * Created by Stephen on 6/13/2017.
 */

@Service
public class NameListService {

    private NameDocument nameDoc;

    private Character cachedCharacter;

    public NameListService() {
        loadLists("names.json");
    }

    public NameListService(String listFileName) {
        loadLists(listFileName);
    }

    public NameListService(NameDocument nameDoc) {
        this.nameDoc = nameDoc;
    }

    private void loadLists(String listFileName) {
        try {
            InputStream nameDocFile = new ClassPathResource(listFileName).getInputStream();

            ObjectMapper mapper = new ObjectMapper();

            nameDoc = mapper.readValue(nameDocFile, NameDocument.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getRandomTitle() {
        return getRandomElement(nameDoc.getTitles());
    }

    public String getRandomPrefix() {
        return getRandomElement(nameDoc.getPrefixes());
    }

    public String getRandomPostfix() {
        return getRandomElement(nameDoc.getPostfixes());
    }

    public String getRandomFeature() {
        return getRandomElement(nameDoc.getFeatures());
    }

    public Character getCachedCharacter() {
        return cachedCharacter;
    }

    private String getRandomElement(List<String> list) {
        return getRandomElement(list, new Random());
    }

    private String getRandomElement(List<String> list, Random rnd) {
        return list.get(rnd.nextInt(list.size()));
    }

    public Character getFullCharacter() {
        String name = (getRandomTitle() + " " + getRandomPrefix() + " " + getRandomPostfix()).trim();
        String item = (getRandomPrefix() + " " + getRandomFeature()).trim();
        return new Character(name, item);
    }

    public Character getFullCharacterSeeded(String seedName) {
        long seed = seedName.chars().reduce(1, (a,b) -> a + b * 2);
        Random rnd = new Random(seed);

        String name = (getRandomElement(nameDoc.getTitles(), rnd) + " "
                + getRandomElement(nameDoc.getPrefixes(), rnd) + " "
                + getRandomElement(nameDoc.getPostfixes(), rnd)).trim();
        String item = (getRandomElement(nameDoc.getPrefixes(), rnd) + " "
                + getRandomElement(nameDoc.getFeatures(), rnd)).trim();
        return new Character(name, item);
    }

    public Character getDailyCharacter() {
        return this.getFullCharacterSeeded(getDailySeed());
    }

    protected String getDailySeed() {
        ZonedDateTime date = Instant.now().atZone(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(date);
    }


    public boolean prefixExists(String prefix) {
        String cleanPrefix = prefix.toLowerCase().trim();
        return nameDoc.getPrefixes().stream().anyMatch(p -> p.toLowerCase().equals(cleanPrefix));
    }

    public boolean postfixExists(String postfix) {
        String cleanPostfix = postfix.toLowerCase().trim();
        return nameDoc.getPostfixes().stream().anyMatch(p -> p.toLowerCase().equals(cleanPostfix));
    }

    public boolean featureExists(String feature) {
        String cleanFeature = feature.toLowerCase().trim();
        return nameDoc.getFeatures().stream().anyMatch(f -> f.toLowerCase().equals(cleanFeature));
    }
}
