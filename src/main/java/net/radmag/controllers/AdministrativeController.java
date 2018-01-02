package net.radmag.controllers;

import net.radmag.services.NameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrativeController {

    private NameListService nls;

    @Autowired
    public AdministrativeController(NameListService nls) {
        this.nls = nls;
    }

    @RequestMapping(value = "prefix/{prefix}/exists", method = RequestMethod.GET)
    public boolean prefixExists(@PathVariable String prefix) {
        return nls.prefixExists(prefix);
    }

    @RequestMapping(value = "postfix/{postfix}/exists", method = RequestMethod.GET)
    public boolean postfixExists(@PathVariable String postfix) {
        return nls.postfixExists(postfix);
    }
}
