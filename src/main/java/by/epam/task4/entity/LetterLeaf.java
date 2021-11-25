package by.epam.task4.entity;

import java.util.List;

public class LetterLeaf implements TextComponent {
    private char letter;
    private TextComponentType type;

    public LetterLeaf(char letter, TextComponentType type) {
        this.letter = letter;
        this.type = type;
    }

    @Override
    public TextComponent getComponentCopy(){
        return new LetterLeaf(this.letter, this.type);
    }

    @Override
    public boolean add(TextComponent textComponent) {
        throw new UnsupportedOperationException("Can't add textComponent " + textComponent + " to letter");
    }

    @Override
    public boolean remove(TextComponent textComponent) {
        throw new UnsupportedOperationException("Can't remove " + textComponent + " from letter");
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Letter is a terminal leaf");
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }
}
