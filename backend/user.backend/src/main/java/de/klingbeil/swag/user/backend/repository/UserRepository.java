package de.klingbeil.swag.user.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.klingbeil.swag.user.backend.model.User;

/**
 * Specifies methods used to obtain and modify user related information which is
 * stored in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds users by using the email as a search criteria.
	 * 
	 * @param email
	 * @return A list of users which email is an exact match with the given
	 *         email. If no user is found, this method returns an empty list.
	 */
	public List<User> findByEmail(String email);
}
