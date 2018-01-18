package net.radmag.controllers;

import net.radmag.model.NameDocument;
import net.radmag.model.Word;
import net.radmag.repositories.WordRepository;
import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.ui.Model;

import java.util.List;

@RestController
public class AdministrativeController {

    private NameListService nls;

    @Autowired
    public AdministrativeController(NameListService nls) {
        this.nls = nls;
    }

    @RequestMapping(value = "admin/lists/{list}/{word}", method = RequestMethod.GET)
    public boolean wordExistsInList(@PathVariable String list, @PathVariable String word) {
        return nls.wordExistsInList(word, list);
    }

    @RequestMapping(value = "admin/lists", method = RequestMethod.POST)
    public String addWord(@RequestBody Word word) {
        if (NameListService.isValidWord(word)) {
            if (nls.saveWord(word.getWord(), word.getType())) {
                return "added " + word.getWord() + " to " + word.getType();
            } else {
                return "could not add " + word.getWord() + " to " + word.getType();
            }
        } else {
            return "invalid submission";
        }
    }

    @PostMapping(value = "admin/bulk_upload")
    public String bulkUpload(@RequestBody List<Word> upload) {
        List<String> failedSaves = nls.addWordsFromBulkUpload(upload);
        return "Failed to save the following words: " + failedSaves.toString();
    }


    @DeleteMapping(value = "admin/lists")
    public boolean deleteAll() {
        return nls.deleteAll();
    }

    @DeleteMapping(value = "admin/lists/{list}")
    public String deleteList(@PathVariable String list) {
        return nls.deleteList(list) ? "deleted " + list : "delete failed";
    }

    @DeleteMapping(value = "admin/lists/{list}/{word}")
    public String deleteWord(@PathVariable String list, @PathVariable String word) {
        return nls.deleteWord(list, word) ? String.format("deleted %s from %s", word, list) : "delete failed";
    }

    @GetMapping(value = "admin/backup")
    public List<Word> databaseBackup() {
        return nls.getDatabaseBackup();
    }

}
