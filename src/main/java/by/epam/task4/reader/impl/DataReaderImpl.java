package by.epam.task4.reader.impl;

import by.epam.task4.exception.CustomException;
import by.epam.task4.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class DataReaderImpl implements DataReader {
    static Logger logger = LogManager.getLogger();

    @Override
    public String readData(String filePath) throws CustomException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource(filePath);
        if (resource == null) {
            logger.error("The source " + filePath + " was not found");
            throw new CustomException("The source " + filePath + " was not found");
        }
        File file = new File(resource.getFile());
        String text = "";
        try {
            text = Files.readString(file.toPath());
        } catch (IOException e) {
            logger.error("Reading from " + filePath + " was failed or interrupted", e);
            throw new CustomException("Reading from " + filePath + " was failed or interrupted", e);
        }
        return text;
    }
}
