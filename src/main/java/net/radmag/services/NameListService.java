package net.radmag.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.radmag.model.Character;
import net.radmag.model.NameDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
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
        loadLists();
        generateCachedCharacter();
    }

    private void loadLists() {
        try {
            InputStream nameDocFile = new ClassPathResource("names.json").getInputStream();

            ObjectMapper mapper = new ObjectMapper();

            nameDoc = mapper.readValue(nameDocFile, NameDocument.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "*/5 * 14 * * *", zone = "GMT-5:00")
    private void generateCachedCharacter() {
        cachedCharacter = getFullCharacter();
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


}
