package by.epam.task4.handler;

import by.epam.task4.entity.TextComponent;

public abstract class AbstractHandler {
    public abstract void handleRequest(TextComponent component, String data);
}
