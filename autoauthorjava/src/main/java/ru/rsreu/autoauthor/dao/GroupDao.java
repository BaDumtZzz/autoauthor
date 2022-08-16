package ru.rsreu.autoauthor.dao;

import ru.rsreu.autoauthor.domain.Group;

import java.util.List;

/**
 * Interface with methods for getting, changing and deleting category data
 */
public interface GroupDao {
    /**
     * Returns all category
     * @return the list of groups
     */
    List<Group> getAll();
    /**
     * Add new category to storage data
     * @param category the name to be added to the storage data
     * @return true if category is successfully added
     */
    boolean save(String category);
    /**
     * Delete category from storage data
     * @param name the name of the category to be removed
     * @return true if category is successfully deleted
     */
    boolean delete(String name);
}
