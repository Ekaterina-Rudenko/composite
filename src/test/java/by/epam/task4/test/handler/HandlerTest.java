package by.epam.task4.test.handler;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.handler.AbstractHandler;
import by.epam.task4.handler.TextHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlerTest {
    TextComponent component;
    AbstractHandler handler;
    String initialText;

    @BeforeMethod
    public void setUp() {
        component = new TextComposite(TextComponentType.TEXT);
        handler = new TextHandler();
        initialText = "    Hashing is a fundamental concept of computer science.\n" +
                "    In Java, efficient hashing algorithms stand behind some of the most popular collections, such as the HashMap (check out this in-depth article) and the HashSet.\n" +
                "    In this tutorial, we'll focus on how hashCode() works, how it plays into collections and how to implement it correctly.\n";
    }

    @Test
    public void textHandlerTest() {
        handler.handleRequest(component, initialText);
        String actualText = component.toString();
        String expectedText = "\tHashing is a fundamental concept of computer science. \n" +
                "\tIn Java, efficient hashing algorithms stand behind some of the most popular collections, such as the HashMap (check out this in-depth article) and the HashSet. \n" +
                "\tIn this tutorial, we'll focus on how hashCode() works, how it plays into collections and how to implement it correctly. \n";
        Assert.assertEquals(actualText, expectedText);
    }
}
