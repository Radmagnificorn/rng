package net.radmag.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stephen on 6/9/2017.
 */

@RestController
public class RandomNameController {

    @RequestMapping(value = "api/v1/name", method = RequestMethod.GET)
    public String getName() {
        return "Fake Name";
    }


}
