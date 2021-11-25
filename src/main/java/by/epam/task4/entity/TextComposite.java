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

    @Override
    public TextComponent getComponentCopy(){
        TextComponent copiedComponent = new TextComposite(this.type);
        this.components.forEach(child -> copiedComponent.add(child.getComponentCopy()));
        return  copiedComponent;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return type != null ? type.equals(that.type) : that.type == null &&
                components != null ? components.equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
        int result = 11;
        result += result * 31 + (type != null ? type.hashCode() : 0);
        result += result * 31 + (components != null ? components.hashCode() : 0);
        return result;
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
