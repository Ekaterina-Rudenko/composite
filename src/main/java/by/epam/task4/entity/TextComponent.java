package by.epam.task4.entity;

import java.util.List;

public interface TextComponent {

    boolean add(TextComponent textComponent);
    boolean remove(TextComponent textComponent);
    List<TextComponent> getComponents();
    TextComponentType getType();
}
