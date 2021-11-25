package by.epam.task4.service;

import by.epam.task4.entity.TextComponent;
import by.epam.task4.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentence(TextComponent text) throws CustomException;
    List<String> findSentenceWithLongestWord(TextComponent text) throws CustomException;
    List <TextComponent> deleteSentencesUnderWordLimit(TextComponent text, int wordLimit) throws CustomException;
    Map<String, Integer> findSameWords(TextComponent text) throws CustomException;
    long countConsonant(TextComponent text) throws CustomException;
    long countVowel(TextComponent text) throws CustomException;
}
