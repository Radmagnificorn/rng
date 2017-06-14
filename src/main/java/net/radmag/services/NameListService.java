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

    @Scheduled(cron = "0 0 0 * * *")
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

    public String getRandomItem() {
        return getRandomElement(nameDoc.getItems());
    }

    public Character getCachedCharacter() {
        return cachedCharacter;
    }

    private String getRandomElement(List<String> list) {
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }

    public Character getFullCharacter() {
        String name = (getRandomTitle() + " " + getRandomPrefix() + " " + getRandomPostfix()).trim();
        String item = (getRandomPrefix() + " " + getRandomItem()).trim();
        return new Character(name, item);
    }


}
