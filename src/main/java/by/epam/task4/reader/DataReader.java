package by.epam.task4.reader;

import by.epam.task4.exception.CustomException;

public interface DataReader {
    String readData(String filePath) throws CustomException;
}
