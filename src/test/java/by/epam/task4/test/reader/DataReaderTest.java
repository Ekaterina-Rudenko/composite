package by.epam.task4.test.reader;

import by.epam.task4.exception.CustomException;
import by.epam.task4.reader.DataReader;
import by.epam.task4.reader.impl.DataReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataReaderTest {
    DataReader reader;
    @BeforeClass
    public void setUp(){
       reader = new DataReaderImpl();
    }

    @Test
    public void readDataTest() throws CustomException {
        String actual = reader.readData("data/test.txt");
        String expected = "    Hashing is a fundamental concept of computer science.\r\n" +
                "    In Java, efficient hashing algorithms stand behind some of the most popular collections, such as the HashMap (check out this in-depth article) and the HashSet.\r\n" +
                "    In this tutorial, we'll focus on how hashCode() works, how it plays into collections and how to implement it correctly.";
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomException.class)
    public void readDataExceptionTest() throws CustomException {
        String actual = reader.readData("data/noFile.txt");
    }

}
