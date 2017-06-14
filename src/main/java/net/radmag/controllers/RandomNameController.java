package net.radmag.controllers;


import net.radmag.model.Character;
import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "rng/character", method = RequestMethod.GET)
    public Character getName() {
       return nls.getFullCharacter();
    }

    @RequestMapping(value = "rng/character/daily", method = RequestMethod.GET)
    public Character getDailyName() {
        return nls.getCachedCharacter();
    }



}
