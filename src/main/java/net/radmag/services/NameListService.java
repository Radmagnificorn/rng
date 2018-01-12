package net.radmag.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.radmag.model.Character;
import net.radmag.model.NameDocument;
import net.radmag.model.Word;
import net.radmag.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Stephen on 6/13/2017.
 */

@Service
public class NameListService {

    private NameDocument nameDoc;

    private WordRepository wordRepository;

    private static final List<String> VALID_LISTS = Arrays.asList(new String[] {
            "title",
            "prefix",
            "postfix",
            "feature"
    });

    @Autowired
    public NameListService(WordRepository wordRepository) {

        this.wordRepository = wordRepository;
        this.nameDoc = loadRepoWords();
    }

    private NameDocument loadRepoWords() {
        List<String> titles = wordRepository.findByType("title").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> prefixes = wordRepository.findByType("prefix").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> postfixes = wordRepository.findByType("postfix").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> features = wordRepository.findByType("feature").stream().map(Word::getWord).collect(Collectors.toList());

        return new NameDocument(titles, prefixes, postfixes, features);
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
        return getRandomElement(loadListOfWords("title"));
    }

    public String getRandomPrefix() {
        return getRandomElement(loadListOfWords("prefix"));
    }

    public String getRandomPostfix() {
        return getRandomElement(loadListOfWords("postfix"));
    }

    public String getRandomFeature() {
        return getRandomElement(loadListOfWords("feature"));
    }

    private String getRandomElement(List<String> list) {
        return getRandomElement(list, new Random());
    }

    private String getRandomElement(List<String> list, Random rnd) {
        if (list != null && list.size() > 0) {
            return list.get(rnd.nextInt(list.size()));
        } else {
            return "";
        }
    }

    public Character getFullCharacter() {
        String name = (getRandomTitle() + " " + getRandomPrefix() + " " + getRandomPostfix()).trim();
        String item = (getRandomPrefix() + " " + getRandomFeature()).trim();
        return new Character(name, item);
    }

    public Character getFullCharacterSeeded(String seedName) {
        long seed = seedName.chars().reduce(1, (a,b) -> a + b * 2);
        Random rnd = new Random(seed);

        List<String> prefixes = loadListOfWords("prefix");

        String name = (getRandomElement(loadListOfWords("title"), rnd) + " "
                + getRandomElement(prefixes, rnd) + " "
                + getRandomElement(loadListOfWords("postfix"), rnd)).trim();
        String item = (getRandomElement(prefixes, rnd) + " "
                + getRandomElement(loadListOfWords("feature"), rnd)).trim();
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

    public static boolean isValidWord(Word word) {
        try {
            return word.getWord() != null && !word.getWord().equals("")
                    && word.getType() != null && !word.getType().equals("");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidList(String listName) {
        return isInList(listName, VALID_LISTS);
    }

    public boolean wordExistsInList(String word, String listName) {
        return (isValidList(listName) && isInList(word, loadListOfWords(listName)));
    }

    private List<String> loadListOfWords(String listName) {
        return wordRepository.findByType(listName).stream().map(Word::getWord).collect(Collectors.toList());
    }

    private boolean isInList(String word, List<String> list) {
        String cleanWord = word.toLowerCase().trim();
        return list.stream().anyMatch(lWord -> lWord.toLowerCase().equals(word));
    }

    public boolean saveWord(String word, String listName) {
        if (isValidList(listName) && !wordExistsInList(word, listName)) {
            wordRepository.save(new Word(word.toLowerCase(), listName));
            return true;
        } else {
            return false;
        }
    }
}
