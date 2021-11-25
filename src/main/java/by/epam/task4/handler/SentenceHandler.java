package by.epam.task4.handler;

import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceHandler extends AbstractHandler {
    private AbstractHandler successor = new LexemeHandler();
    static final String REGEX_SENTENCE = "(\\w|[А-ЯЁ]).+?(?<=[.!?]{1,3})(\\s|$)";

    @Override
    public void handleRequest(TextComponent paragraphComponent, String data) {
        Pattern pattern = Pattern.compile(REGEX_SENTENCE);
        Matcher sentenceMatcher = pattern.matcher(data);

        while (sentenceMatcher.find()) {
                String sentence = sentenceMatcher.group();
                TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
                paragraphComponent.add(sentenceComponent);
                successor.handleRequest(sentenceComponent, sentence);
        }
    }
}
