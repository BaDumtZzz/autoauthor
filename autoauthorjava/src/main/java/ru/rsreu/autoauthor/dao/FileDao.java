package ru.rsreu.autoauthor.dao;

import ru.rsreu.autoauthor.domain.Author;
import ru.rsreu.autoauthor.domain.File;

import java.util.List;

public interface FileDao {
    /**
     * Returns all category
     * @return the list of groups
     */
    List<File> getAll();
    /**
     * Add new category to storage data
     * @param author the name to be added to the storage data
     * @return true if category is successfully added
     */
    boolean save(File author);
    /**
     * Delete category from storage data
     * @param name the name of the category to be removed
     * @return true if category is successfully deleted
     */
    boolean delete(String name);
}
