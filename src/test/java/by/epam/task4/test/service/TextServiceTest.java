package by.epam.task4.test.service;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.exception.CustomException;
import by.epam.task4.handler.AbstractHandler;
import by.epam.task4.handler.TextHandler;
import by.epam.task4.service.TextService;
import by.epam.task4.service.impl.TextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextServiceTest {

    AbstractHandler handler;
    TextComposite component;
    TextService service;

    @BeforeMethod
    public void setUp() {
        String initialText = "    Hashing is a fundamental concept of computer science.\n" +
                "    In Java, efficient hashing algorithms stand behind some of the most popular collections, such as the HashMap (check out this in-depth article) and the HashSet.\n" +
                "    In this tutorial, we'll focus on how hashCode() works, how it plays into collections and how to implement it correctly. The simplest operations on collections can be inefficient in certain situations.\n" +
                "    A naive hashCode() implementation that fully adheres to the above contract is actually quite straightforward.\n";
        handler = new TextHandler();
        component = new TextComposite(TextComponentType.TEXT);
        handler.handleRequest(component, initialText);
        service = new TextServiceImpl();
    }

    @Test
    public void sortParagraphsBySentenceTest() throws CustomException {
        List<TextComponent> actual = service.sortParagraphsBySentence(component);
        List<TextComponent> expected = new ArrayList<>();
        expected.add(component.getComponents().get(0));
        expected.add(component.getComponents().get(1));
        expected.add(component.getComponents().get(3));
        expected.add(component.getComponents().get(2));
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void findSentenceWithLongestWordTest() throws CustomException {
        List<String> actual = service.findSentenceWithLongestWord(component);
        List<String> expected = new ArrayList<>();
        expected.add("A naive hashCode() implementation that fully adheres to the above contract is actually quite straightforward. ");
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void deleteSentencesUnderWordLimitTest() throws CustomException {
        List<TextComponent> actual = service.deleteSentencesUnderWordLimit(component, 24);
        List<TextComponent> expected = new ArrayList<>();
        expected.add(component.getComponents().get(1).getComponents().get(0));
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void findSameWordsTest() throws CustomException {
        Map<String, Integer> actual = service.findSameWords(component);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("hashing", 2);
        expected.put("collections", 3);
        expected.put("in", 4);
        expected.put("hashcode", 2);
        expected.put("this", 2);
        expected.put("is", 2);
        expected.put("it", 2);
        expected.put("how", 3);
        expected.put("and", 2);
        expected.put("of", 2);
        expected.put("on", 2);
        expected.put("a", 2);
        expected.put("the", 5);
        expected.put("to", 2);
        Assert.assertEqualsDeep(actual, expected);
    }

    @Test
    public void countVowelTest() throws CustomException {
        long actual = service.countVowel(component);
        long expected = 164;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countConsonantTest() throws CustomException {
        long actual = service.countConsonant(component);
        long expected = 265;
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomException.class)
    public void serviceMethodNullTest() throws CustomException {
        long actual = service.countConsonant(null);
    }

    @Test(expectedExceptions = CustomException.class)
    public void serviceMethodIncorrectTypeTest() throws CustomException {
        long actual = service.countConsonant(new TextComposite(TextComponentType.PUNCTUATION));
    }

}
