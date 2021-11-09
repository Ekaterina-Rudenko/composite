package by.epam.task4.handler;

import by.epam.task4.entity.TextComponentType;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComposite;

public class ParagraphHandler extends AbstractHandler{
    AbstractHandler successor = new SentenceHandler();
    static final String REGEX_PARAGRAPH_SPLITTER = "\n\s{4}";
    @Override
    public void handleRequest(TextComponent textComponent, String data){
        data = data.trim();
        String[] paragraphs = data.split(REGEX_PARAGRAPH_SPLITTER);
        for(String paragraph : paragraphs){
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            textComponent.add(paragraphComponent);
            successor.handleRequest(paragraphComponent, paragraph);
        }
    }
}
