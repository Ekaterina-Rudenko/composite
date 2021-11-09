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
import java.util.stream.Stream;

public class TextServiceImpl implements TextService {
    static final Logger logger = LogManager.getLogger();
    static final String CONSONANT_REGEX = "(?i)(?u)[\\p{Alpha}б-ь&&[^aeiouyеёиоуы]]";
    static final String VOWEL_REGEX = "(?iu)[aeiouyаеёиоуыэ-я]";

    @Override
    public List<TextComponent> sortParagraphsBySentence(TextComponent text) throws CustomException {
        if (text == null | !text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type " + text.getType() + " of text component");
        }
        List<TextComponent> paragraphs = text.getComponents();

        paragraphs.sort(new Comparator<TextComponent>() {
            @Override
            public int compare(TextComponent o1, TextComponent o2) {
                Integer size1 = o1.getComponents().size();
                Integer size2 = o2.getComponents().size();
                return size1.compareTo(size2);
            }
        });
        return paragraphs;
    }

    @Override
    public TextComponent findSentenceWithLongestWord(TextComponent text) throws CustomException {
        if (text == null | !text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type " + text.getType() + " of text component");
        }
        List<TextComponent> sentences = text.getComponents().stream()
                .flatMap(par -> par.getComponents().stream())
                .collect(Collectors.toList());

        List<TextComponent> words = sentences.stream()
                .flatMap(sentence -> sentence.getComponents().stream()) //get lexemes
                .flatMap(lexeme -> lexeme.getComponents().stream()) //get words or punctuation
                .filter(word -> !(word instanceof PunctuationLeaf)) //exclude punctuation
                .collect(Collectors.toList());

        TextComponent longestWord = words.stream()
                .max(new Comparator<TextComponent>() {
                    @Override
                    public int compare(TextComponent o1, TextComponent o2) {
                        Integer size1 = o1.getComponents().size();
                        Integer size2 = o2.getComponents().size();
                        return size1.compareTo(size2);
                    }
                }).orElseThrow(() -> new CustomException("Stream of words is empty"));

        String longestWordString = longestWord.toString();
        logger.info("The longest word:\n" + longestWordString);

        for (TextComponent sentence : sentences) {
            String sentenceString = sentence.toString();
            if (sentenceString.contains(longestWordString)) {
                return sentence;
            }
        }
        throw new CustomException("This exception will not be thrown");
    }

    @Override
    public List<TextComponent> deleteSentencesUnderWordLimit(TextComponent text, int wordLimit) throws CustomException {
        if (text == null | !text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type " + text.getType() + " of text component");
        }
        List<TextComponent> sentences = text.getComponents().stream()
                .flatMap(par -> par.getComponents().stream())
                .collect(Collectors.toList());

        List<TextComponent> correctSentences = sentences.stream()
                .filter(s -> countWords(s) > wordLimit)
                .collect(Collectors.toList());

        return correctSentences;
    }

    private int countWords(TextComponent sentence) {
        long numberOfWords = sentence.getComponents().stream()
                .flatMap(l -> l.getComponents().stream())
                .filter(w -> !(w instanceof PunctuationLeaf))
                .count();
        return (int) numberOfWords;
    }

    @Override
    public Map<String, Integer> findSameWords(TextComponent text) {
        return null;
    }

    @Override
    public long countConsonant(TextComponent text) throws CustomException {
        if (text == null | !text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type " + text.getType() + " of text component");
        }
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);
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

    @Override
    public long countVowel(TextComponent text) throws CustomException {
        if (text == null | !text.getType().equals(TextComponentType.TEXT)) {
            throw new CustomException("Incorrect type " + text.getType() + " of text component");
        }
        Pattern pattern = Pattern.compile(VOWEL_REGEX);
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
}
