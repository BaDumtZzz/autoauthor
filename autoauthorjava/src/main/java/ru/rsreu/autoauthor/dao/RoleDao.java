package ru.rsreu.autoauthor.dao;

import java.util.List;

/**
 * Interface with methods for getting role data
 */
public interface RoleDao {
    /**
     * Returns all role
     * @return the list of role
     */
    List<String> getAll();
}
