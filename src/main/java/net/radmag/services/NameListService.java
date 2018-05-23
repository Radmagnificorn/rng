package net.radmag.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.radmag.model.Character;
import net.radmag.model.NameDocument;
import net.radmag.model.Word;
import net.radmag.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Stephen on 6/13/2017.
 */

@Service
public class NameListService {

    private NameDocument nameDoc;

    private WordRepository wordRepository;

    private static final List<String> VALID_LIST_NAMES = Arrays.asList(new String[] {
            "prefix",
            "postfix",
            "feature",
            "feature_description"
    });

    @Autowired
    public NameListService(WordRepository wordRepository) {

        this.wordRepository = wordRepository;
        this.nameDoc = loadRepoWords();
    }

    private NameDocument loadRepoWords() {
        List<String> prefixes = wordRepository.findByType("prefix").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> postfixes = wordRepository.findByType("postfix").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> featureDescriptions = wordRepository.findByType("feature_descriptions").stream().map(Word::getWord).collect(Collectors.toList());
        List<String> features = wordRepository.findByType("feature").stream().map(Word::getWord).collect(Collectors.toList());

        return new NameDocument(featureDescriptions, prefixes, postfixes, features);
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

    private String getRandomElement(List<String> list, Random rnd) {
        if (list != null && list.size() > 0) {
            return list.get(rnd.nextInt(list.size()));
        } else {
            return "";
        }
    }

    private static final long RANDOM = -1;
    public Character getFullCharacter() {
        return getFullCharacter(RANDOM);
    }

    public Character getFullCharacter(String seedName) {
        long seed = seedName.chars().reduce(1, (a,b) -> a + b * 2);
        return getFullCharacter(seed);
    }

    public Character getFullCharacter(long seed) {
        Random rnd = seed != RANDOM ? new Random(seed) : new Random();

        String name = (getRandomElement(loadListOfWords("prefix"), rnd) + " "
                + getRandomElement(loadListOfWords("postfix"), rnd)).trim();
        String item = (getRandomElement(loadListOfWords("feature_description"), rnd) + " "
                + getRandomElement(loadListOfWords("feature"), rnd)).trim();
        return new Character(name, item);
    }

    public Character getDailyCharacter() {
        return this.getFullCharacter(getDailySeed());
    }

    protected long getDailySeed() {
        ZonedDateTime date = Instant.now().atZone(ZoneId.of("America/New_York"));
        return date.truncatedTo(ChronoUnit.DAYS).toEpochSecond();
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
        return isInList(listName, VALID_LIST_NAMES);
    }

    public boolean wordExistsInList(String word, String listName) {
        return (isValidList(listName) && isInList(word, loadListOfWords(listName)));
    }

    private List<String> loadListOfWords(String listName) {
        return wordRepository.findByType(listName).stream().map(Word::getWord).collect(Collectors.toList());
    }

    private boolean isInList(String word, List<String> list) {
        String cleanWord = word.toLowerCase().trim();
        return list.stream().anyMatch(lWord -> lWord.toLowerCase().equals(cleanWord));
    }

    public boolean saveWord(String word, String listName) {
        if (isValidList(listName) && !wordExistsInList(word.toLowerCase(), listName)) {
            wordRepository.save(new Word(word.toLowerCase(), listName));
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            wordRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteWord(String type, String word) {
        return wordRepository.deleteWordByTypeAndWord(type, word) > 0;
    }

    public boolean deleteList(String list) {
        return wordRepository.deleteWordsByType(list).size() > 0;
    }

    public List<String> addWordsFromBulkUpload(List<Word> upload) {
        return upload.stream().flatMap(word -> {
            boolean isSaved = saveWord(word.getWord(), word.getType());
            return !isSaved ? Stream.of(word.getWord()) : Stream.empty();
        }).collect(Collectors.toList());
    }

    public List<String> addWordsToList(String type, List<String> words) {
        return words.stream().flatMap(word -> {
            boolean isSaved = saveWord(word, type);
            return !isSaved ? Stream.of(word) : Stream.empty();
        }).collect(Collectors.toList());
    }

    public List<Word> getDatabaseBackup() {
        List<Word> backup = new ArrayList<>();
        wordRepository.findAll().iterator().forEachRemaining(backup::add);
        return backup;
    }
}
