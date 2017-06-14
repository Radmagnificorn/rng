package net.radmag.controllers;


import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

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

    @RequestMapping(value = "rng/name", method = RequestMethod.GET)
    public String getName() {
       return nls.getRandomTitle() + " " + nls.getRandomPrefix() + " " + nls.getRandomPostfix();
    }

    @RequestMapping(value = "rng/daily", method = RequestMethod.GET)
    public String getDailyName() {
        return nls.getCachedName();
    }



}
