package by.epam.task4.handler;

import by.epam.task4.entity.TextComponent;

public class TextHandler extends AbstractHandler {
    AbstractHandler successor = new ParagraphHandler();
    @Override
    public void handleRequest(TextComponent text, String data){
        successor.handleRequest(text, data);
    }
}
