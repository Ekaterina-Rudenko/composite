package by.epam.task4.service.impl;

import by.epam.task4.entity.PunctuationLeaf;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;
import by.epam.task4.exception.CustomException;
import by.epam.task4.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    static final Logger logger = LogManager.getLogger();
    static final String CONSONANT_REGEX = "(?i)(?u)[\\p{Alpha}б-ь&&[^aeiouyеёиоуы]]";
    static final String VOWEL_REGEX = "(?iu)[aeiouyаеёиоуыэ-я]";

    @Override
    public List<TextComponent> sortParagraphsBySentence(TextComponent text) throws CustomException {
        validateTextComponent(text);
        List<TextComponent> paragraphs = text.getComponents();
        paragraphs.sort(Comparator.comparingInt(paragraph -> paragraph.getComponents().size()));
        return paragraphs;
    }

    @Override
    public List<String> findSentenceWithLongestWord(TextComponent text) throws CustomException {
        validateTextComponent(text);
        TextComponent longestWord = getWords(text).stream()
                .max(Comparator.comparingInt(word -> word.getComponents().size()))
                .orElseThrow(() -> new CustomException("Stream of words is empty"));

        String longestWordString = longestWord.toString();
        logger.info("The longest word:\n" + longestWordString);

        List<String> resultSentence = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .map(s -> s.toString())
                .filter(s -> s.contains(longestWordString))
                .collect(Collectors.toList());
        return resultSentence;
    }

    @Override
    public List<TextComponent> deleteSentencesUnderWordLimit(TextComponent text, int wordLimit) throws CustomException {
        validateTextComponent(text);
        TextComponent textCopy = text.getComponentCopy();
        List<TextComponent> correctSentences = textCopy.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream()
                        .filter(sentence -> countWords(sentence) > wordLimit))
                .collect(Collectors.toList());
        return correctSentences;
    }

    @Override
    public Map<String, Integer> findSameWords(TextComponent text) throws CustomException {
        validateTextComponent(text);
        List<String> words = getWords(text).stream()
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toList());
        Map<String, Integer> sameWords = new HashMap<>();

        for (String word : words) {
            if (sameWords.isEmpty() | !sameWords.containsKey(word)) {
                sameWords.put(word, 1);
            } else if (sameWords.containsKey(word)) {
                int value = sameWords.get(word);
                sameWords.put(word, ++value);
            }
        }
        Iterator<String> iterator = sameWords.keySet().iterator();
        while (iterator.hasNext()) {
            String currentWord = iterator.next();
            if (sameWords.get(currentWord) == 1) {
                iterator.remove();
            }
        }
        return sameWords;
    }

    @Override
    public long countConsonant(TextComponent text) throws CustomException {
        validateTextComponent(text);
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);
        long consonantNumber = countLetters(text, pattern);
        return consonantNumber;
    }

    @Override
    public long countVowel(TextComponent text) throws CustomException {
        validateTextComponent(text);
        Pattern pattern = Pattern.compile(VOWEL_REGEX);
        long vowelNumber = countLetters(text, pattern);
        return vowelNumber;
    }

    private List<TextComponent> getWords(TextComponent text) {
        List<TextComponent> words = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .flatMap(s -> s.getComponents().stream())
                .flatMap(l -> l.getComponents().stream())
                .filter(w -> !(w instanceof PunctuationLeaf))
                .collect(Collectors.toList());
        return words;
    }

    private int countWords(TextComponent sentence) {
        long numberOfWords = sentence.getComponents().stream()
                .flatMap(l -> l.getComponents().stream())
                .filter(w -> !(w instanceof PunctuationLeaf))
                .count();
        return (int) numberOfWords;
    }

    private long countLetters(TextComponent text, Pattern pattern) {
        long counter = text.getComponents().stream()
                .flatMap(p -> p.getComponents().stream())
                .flatMap(s -> s.getComponents().stream())
                .flatMap(l -> l.getComponents().stream()
                        .filter(w -> !(w instanceof PunctuationLeaf)))
                .flatMap(w -> w.getComponents().stream())
                .map(let -> let.toString())
                .filter(let -> pattern.matcher(let).matches())
                .count();
        return counter;
    }

    private void validateTextComponent(TextComponent text) throws CustomException {
        if (text == null) {
            throw new CustomException("Null value of text component.");
        } else if (!text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type of text component: " + text.getType() + ". TEXT type is expected.");
        }
    }
}
