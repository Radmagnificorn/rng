package net.radmag.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.radmag.model.NameDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Stephen on 6/13/2017.
 */

@Service
public class NameListService {

    private NameDocument nameDoc;

    private String cachedName;

    public NameListService() {
        loadLists();
        generateCachedName();
    }

    private void loadLists() {
        try {
            File nameDocFile = new ClassPathResource("names.json").getFile();

            ObjectMapper mapper = new ObjectMapper();

            nameDoc = mapper.readValue(nameDocFile, NameDocument.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "*/5 33 14 * * *")
    private void generateCachedName() {
        cachedName = getRandomTitle() + " " + getRandomPrefix() + " " + getRandomPostfix();
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

    public String getCachedName() {
        return cachedName;
    }

    private String getRandomElement(List<String> list) {
        Random rnd = new Random();
        return list.get(rnd.nextInt(list.size()));
    }


}
