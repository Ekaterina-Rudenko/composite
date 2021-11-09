package by.epam.task4.handler;

import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.PunctuationLeaf;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordPunctuationHandler extends AbstractHandler {
    AbstractHandler successor = new LetterHandler();
    private static final String WORD_REGEX = "[\\wа-яА-яёЁ]+";
    private static final String PUNCTUATION_REGEX = "\\p{P}|\n";
    private static final String REGEX_WORD_OR_PUNCTUATION = "([\\wа-яА-яёЁ]+)|(\\p{Punct})";

    @Override
    public void handleRequest(TextComponent lexemeComponent, String data) {
        Pattern pattern = Pattern.compile(REGEX_WORD_OR_PUNCTUATION);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String part = matcher.group();

            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(part);

            if (wordMatcher.matches()) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                lexemeComponent.add(wordComponent);
                successor.handleRequest(wordComponent, part);
            } else {
                TextComponent punctuationComponent = new PunctuationLeaf(part.charAt(0), TextComponentType.PUNCTUATION);
                lexemeComponent.add(punctuationComponent);
            }
        }
    }
}
