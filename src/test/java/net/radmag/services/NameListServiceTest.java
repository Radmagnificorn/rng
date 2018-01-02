package net.radmag.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.radmag.model.NameDocument;
import org.junit.Test;

import java.util.Arrays;

public class NameListServiceTest {

    NameListService nls;

    public NameListServiceTest() {
        NameDocument nameDoc = generateTestNameDoc();
        nls = new NameListService(nameDoc);
    }

    private NameDocument generateTestNameDoc() {
        NameDocument nameDoc = new NameDocument();
        nameDoc.setPrefixes(Arrays.asList(new String[] {"Red", "Yellow", "Blue"}));
        nameDoc.setPostfixes(Arrays.asList(new String[] {"Man", "Woman", "Child"}));
        nameDoc.setFeatures(Arrays.asList(new String[] {"Bow", "Sword", "Hat"}));
        nameDoc.setTitles(Arrays.asList(new String[] {"The", "Some", "A"}));
        return nameDoc;
    }

    @Test
    public void testPrefixExists() {
        assertTrue(nls.prefixExists("Red"));
        assertFalse(nls.prefixExists("Green"));
    }
}
