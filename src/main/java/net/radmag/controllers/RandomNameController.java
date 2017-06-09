package net.radmag.controllers;

import net.radmag.model.NamePart;
import net.radmag.repository.NamePartRepository;
import net.radmag.repository.RNGRepositoryStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final NamePartRepository namePartRepository;

    @Autowired
    public RandomNameController(NamePartRepository namePartRepository) {
        this.namePartRepository = namePartRepository;
    }

    @RequestMapping(value = "api/v1/name", method = RequestMethod.GET)
    public String getName() {
        RNGRepositoryStub repo = new RNGRepositoryStub();

        List<NamePart> prefixes = repo.queryTag("prefix");
        List<NamePart> postfixes = repo.queryTag("postfix");

        String prefix = getRandomWord(prefixes);
        String postfix = getRandomWord(postfixes);

        return prefix + " " + postfix;
    }

    @RequestMapping(value = "api/v1/nameparts", method = RequestMethod.POST)
    public String create(@RequestBody NamePart namePart) {
        namePartRepository.save(namePart);
        return namePart.getWord();
    }

    @RequestMapping(value = "api/v1/nameparts", method = RequestMethod.GET)
    public String getAll() {
        return namePartRepository.findAll().toString();
    }

    private String getRandomWord(List<NamePart> nameParts) {
        int rndIndex = (new Random()).nextInt(nameParts.size());
        return nameParts.get(rndIndex).getWord();
    }


}
