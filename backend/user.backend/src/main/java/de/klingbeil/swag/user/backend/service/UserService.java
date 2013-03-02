package de.klingbeil.swag.user.backend.service;

import java.util.List;

import de.klingbeil.swag.user.backend.model.User;

/**
 * Declares methods used to obtain and modify user information.
 */
public interface UserService {

	/**
	 * Creates a new user.
	 * 
	 * @param user
	 *            The information of the created person.
	 * @return The created user.
	 */
	public User create(User user);

	/**
	 * Deletes a user.
	 * 
	 * @param id
	 *            The id of the user to delete.
	 * @throws IllegalArgumentException
	 *             if user with userId not found
	 */
	public void delete(Long id);

	/**
	 * Updates the information of a user.
	 * 
	 * @param updatedUser
	 *            user with updated information.
	 * @return The updated user.
	 */
	public User update(User updatedUser);

	/**
	 * Finds all users.
	 * 
	 * @return A list of users.If no user is found, this method returns an empty
	 *         list.
	 */
	public List<User> findAll();

	/**
	 * Finds user by id.
	 * 
	 * @param id
	 *            The id of the user to find.
	 * @return The user. If no user is found, this method returns null.
	 * @throws IllegalArgumentException
	 *             if user with id not found
	 */
	public User findById(Long id);

	/**
	 * Finds users by their email.
	 * 
	 * @param email
	 * @return A list of users which email is an exact match with the given
	 *         email. If no user is found, this method returns an empty list.
	 */
	public List<User> findByEmail(String email);
}
