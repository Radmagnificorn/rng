package net.radmag.repository;

import net.radmag.model.NamePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Stephen on 6/9/2017.
 */
public class RNGRepositoryStub {

    private List<NamePart> nameParts;

    public RNGRepositoryStub() {
        nameParts = new ArrayList<NamePart>();
        populateNameList();
    }

    public void add(NamePart part) {
        nameParts.add(part);
    }

    public List<NamePart> queryTag(String tag) {
        return nameParts.stream().filter(namePart -> namePart.getTags()
                .contains(tag))
                .collect(Collectors.toList());
    }

    private void populateNameList() {

        //prefixes
        nameParts.add(new NamePart("red", Arrays.asList("prefix", "color")));
        nameParts.add(new NamePart("yellow", Arrays.asList("prefix", "color")));
        nameParts.add(new NamePart("blue", Arrays.asList("prefix", "color")));

        //postfixes
        nameParts.add(new NamePart("captain", Arrays.asList("postfix")));
        nameParts.add(new NamePart("warrior", Arrays.asList("postfix")));
        nameParts.add(new NamePart("dandy", Arrays.asList("postfix")));
    }

}
