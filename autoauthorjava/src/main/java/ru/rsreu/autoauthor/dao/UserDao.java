package ru.rsreu.autoauthor.dao;

import ru.rsreu.autoauthor.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface with methods for getting, changing and deleting user data
 */
public interface UserDao {
    /**
     * Returns all users
     * @return the list of users
     */
    List<User> getAll();

    /**
     * Returns the user with the corresponding id
     * @param id the id of the user to be returned
     * @return the user with the corresponding id
     */
    Optional<User> getById(String id);

    /**
     * Add new user to storage data
     * @param user comment to be added to the storage data
     * @return true if user is successfully deleted
     */
    boolean save(User user);

    /**
     * Delete  user from storage data
     * @param userId the id of the user to be removed
     * @return true if user is successfully deleted
     */
    boolean delete(String userId);

    /**
     * Set status "blocked" to user with the corresponding id
     * @param userId the id of the user to be blocked
     * @return true if user is successfully blocked
     */
    boolean block(String userId);

    /**
     * Set status "offline" to user with the corresponding id
     * @param userId the id of the user to be unlocked
     * @return true if user is successfully unlocked
     */
    boolean unlock(String userId);

    /**
     * Set status "online" to user with the corresponding id
     * @param userId the id of the user whose rating will be changed
     * @return true if active status was successfully changed
     */
    boolean setOnlineStatus(String userId);

    /**
     * Set status "offline" to user with the corresponding id
     * @param userId the id of the user whose rating will be changed
     * @return true if active status was successfully changed
     */
    boolean setOfflineStatus(String userId);

    /**
     * Changes the user's email and role with the corresponding id
     * @param id  the id of the user whose email and role will be changed
     * @param email new user email
     * @param role new user role
     * @return true if email and role was successfully changed
     */
    boolean updateUserInformation(String id, String email, String role);
}
