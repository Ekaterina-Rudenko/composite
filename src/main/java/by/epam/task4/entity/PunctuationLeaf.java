package by.epam.task4.entity;

import java.util.List;

public class PunctuationLeaf implements TextComponent {
    private char punctuation;
    private TextComponentType type;

    public PunctuationLeaf(char punctuation, TextComponentType type) {
        this.punctuation = punctuation;
        this.type = type;
    }

    @Override
    public TextComponent getComponentCopy(){
        return new PunctuationLeaf(this.punctuation, this.type);
    }

    @Override
    public boolean add(TextComponent textComponent) {
        throw new UnsupportedOperationException("Component " + textComponent + " can't be added to punctuation symbol");
    }

    @Override
    public boolean remove(TextComponent textComponent) {
        throw new UnsupportedOperationException("Component " + textComponent + " can't be removed from punctuation symbol");
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Punctuation leaf ia a terminal element");
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(punctuation);
    }
}
