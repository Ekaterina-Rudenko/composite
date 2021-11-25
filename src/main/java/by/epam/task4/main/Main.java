package by.epam.task4.main;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.exception.CustomException;
import by.epam.task4.handler.AbstractHandler;
import by.epam.task4.handler.TextHandler;
import by.epam.task4.reader.DataReader;
import by.epam.task4.reader.impl.DataReaderImpl;
import by.epam.task4.service.TextService;
import by.epam.task4.service.impl.TextServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {
    static Logger logger = LogManager.getLogger();
    static final String FILE_PATH = "data/data.txt";
    static final int WORD_LIMIT = 19;

    public static void main(String[] args) {
        DataReader reader = new DataReaderImpl();
        String text;
        try {
            text = reader.readData(FILE_PATH);
            System.out.println(text);
            AbstractHandler handler = new TextHandler();
            TextComponent component = new TextComposite(TextComponentType.TEXT);
            handler.handleRequest(component, text);
            logger.info("Parsed data:\n" + component);
            TextService service = new TextServiceImpl();
            List<TextComponent> componentList = service.sortParagraphsBySentence(component);
            logger.info("Sorting by the number of sentences in the paragraph:\n" + componentList);
            List<String> sentenceWithLongestWord = service.findSentenceWithLongestWord(component);
            logger.info("Sentence with the longest word:\n" + sentenceWithLongestWord);
            List<TextComponent> sentencesAboveWordLimit = service.deleteSentencesUnderWordLimit(component, WORD_LIMIT);
            logger.info("Sentence with the number of words more than " + WORD_LIMIT + ":\n" + sentencesAboveWordLimit);
            long numberOfConsonant = service.countConsonant(component);
            logger.info("Number of consonants in the text:" + numberOfConsonant);
            long numberOfVowel = service.countVowel(component);
            logger.info("Number of vowels in the text:" + numberOfVowel);
            Map<String, Integer> sameWords = service.findSameWords(component);
            logger.info("Same words: " + sameWords);
        } catch (CustomException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
