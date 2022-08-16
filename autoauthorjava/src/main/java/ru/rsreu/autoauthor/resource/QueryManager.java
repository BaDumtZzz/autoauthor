package ru.rsreu.autoauthor.resource;

import java.util.ResourceBundle;

public class QueryManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");
    // класс извлекает информацию из файла queries.properties
    private QueryManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
