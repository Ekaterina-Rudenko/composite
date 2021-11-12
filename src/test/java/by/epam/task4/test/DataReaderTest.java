package by.epam.task4.test;

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
        String actual = reader.readData("data/testReader.txt");
        String expected = "    An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.";
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomException.class)
    public void readDataExceptionTest() throws CustomException {
        String actual = reader.readData("data/noFile.txt");
    }

}
