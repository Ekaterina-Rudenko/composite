package by.epam.task4.handler;

import by.epam.task4.entity.LetterLeaf;
import by.epam.task4.entity.TextComponent;
import by.epam.task4.entity.TextComponentType;

public class LetterHandler extends AbstractHandler{
    @Override
    public void handleRequest(TextComponent wordComponent, String data){
        char[] letters = data.toCharArray();
        for(char letter : letters){
            TextComponent letterComponent = new LetterLeaf(letter, TextComponentType.LETTER);
            wordComponent.add(letterComponent);
        }
    }
}
