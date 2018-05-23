package net.radmag.controllers;

import net.radmag.model.Character;
import net.radmag.model.NameDocument;
import net.radmag.services.NameListService;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

/**
 * Created by Stephen on 6/9/2017.
 */
public class RandomNameControllerTest {
/*
    private NameDocument generateTestNameDoc() {
        NameDocument nameDoc = new NameDocument();
        nameDoc.setPrefixes(Arrays.asList(new String[] {"Red", "Yellow", "Blue"}));
        nameDoc.setPostfixes(Arrays.asList(new String[] {"Man", "Woman", "Child"}));
        nameDoc.setFeatures(Arrays.asList(new String[] {"Bow", "Sword", "Hat"}));
        nameDoc.setTitles(Arrays.asList(new String[] {"The", "Some", "A"}));
        return nameDoc;
    }

    @Test
    public void getCharacterReturnsAValidCharacter() {
        NameListService nls = new NameListService(generateTestNameDoc());
        RandomNameController rnc = new RandomNameController(nls);
        Character result = rnc.getCharacter();
        assertThat(result.getCharacter(), anyOf(
                containsString("Red"),
                containsString("Yellow"),
                containsString("Blue")));
        assertThat(result.getCharacter(), anyOf(
                containsString("Man"),
                containsString("Woman"),
                containsString("Child")));
        assertThat(result.getKeyFeature(), anyOf(
                containsString("Bow"),
                containsString("Sword"),
                containsString("Hat")));

    }

    @Test
    public void getCharacterReturnsARandomCharacter() {
        NameListService nls = new NameListService(generateTestNameDoc());
        RandomNameController rnc = new RandomNameController(nls);
        Character result1 = rnc.getCharacter();
        Character result2 = rnc.getCharacter();
        Character result3 = rnc.getCharacter();

        boolean allEqual = (result1.getCharacter().equals(result2.getCharacter())
                && result1.getKeyFeature().equals(result2.getKeyFeature())
                && result2.getCharacter().equals(result3.getCharacter())
                && result2.getKeyFeature().equals(result3.getKeyFeature()));

        assertThat(allEqual, equalTo(false));
    }

    @Test
    public void getDailyCharacterIsSameForEachDate() {
        NameListService nls = new NameListService(generateTestNameDoc()) {
            @Override
            protected long getDailySeed() {
                return 123456789;
            }
        };

        RandomNameController rnc = new RandomNameController(nls);
        assertThat(rnc.getDailyCharacter().toString(), equalTo("The Blue Man with the Yellow Hat"));
    }

    @Test
    public void getCharacterByNameIsSameForEachName() {
        NameListService nls = new NameListService(generateTestNameDoc());
        RandomNameController rnc = new RandomNameController(nls);
        System.out.println(rnc.getCharacterByName("Bruce Wayne"));
        assertThat(rnc.getCharacterByName("Bruce Wayne").toString(), equalTo("A Blue Woman with the Blue Bow"));
    }
*/
}
