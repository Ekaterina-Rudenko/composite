package by.epam.task4.handler;

import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;

public class LexemeHandler extends AbstractHandler {
    AbstractHandler successor = new WordPunctuationHandler();
    private static final String REGEX_LEXEME_DELIMITER = "\\s";

    @Override
    public void handleRequest(TextComponent sentenceComponent, String data) {
        data = data.trim();
        String[] lexemes = data.split(REGEX_LEXEME_DELIMITER);
        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            sentenceComponent.add(lexemeComponent);
            successor.handleRequest(lexemeComponent, lexeme);
        }
    }
}
