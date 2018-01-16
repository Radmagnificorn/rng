package net.radmag.controllers;

import net.radmag.model.Word;
import net.radmag.repositories.WordRepository;
import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.ui.Model;

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

}
