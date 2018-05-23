package net.radmag.controllers;


import net.radmag.model.Character;
import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stephen on 6/9/2017.
 */

@RestController
public class RandomNameController {

    private NameListService nls;

    @Autowired
    public RandomNameController(NameListService nameListService) {
        this.nls = nameListService;
    }

    @RequestMapping(value = "character", method = RequestMethod.GET)
    public Character getCharacter() {
       return nls.getFullCharacter();
    }

    @RequestMapping(value = "character/daily", method = RequestMethod.GET)
    public Character getDailyCharacter() {
        return nls.getDailyCharacter();
    }

    @RequestMapping(value = "character/name/{name}", method = RequestMethod.GET)
    public Character getCharacterByName(@PathVariable String name) {
        return nls.getFullCharacter(name);
    }



}
