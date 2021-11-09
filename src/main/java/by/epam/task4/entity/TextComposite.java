package by.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private TextComponentType type;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean add(TextComponent textComponent) {
        return components.add(textComponent);
    }

    @Override
    public boolean remove(TextComponent textComponent) {
        return components.remove(textComponent);
    }

    @Override
    public List<TextComponent> getComponents() {
        return new ArrayList<TextComponent>(components);
    }

    public TextComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(type.getPrefix());
        for (TextComponent component : components) {
            stringBuilder.append(component.toString());
        }
        stringBuilder.append(type.getPostfix());
        return stringBuilder.toString();
    }

}
