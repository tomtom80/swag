package de.klingbeil.swag.user.backend.service.internal;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.repository.UserRepository;
import de.klingbeil.swag.user.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Resource
	UserRepository userRepository;

	@Transactional
	@Override
	public User create(User user) {
		Assert.notNull(user, "user must not be null");

		LOGGER.debug("Creating a new user: " + user);
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Assert.notNull(id, "id must not be null");

		User userToDelete = findById(id);
		LOGGER.debug("Deleteing a user: " + userToDelete);
		userRepository.delete(userToDelete);
	}

	@Transactional
	@Override
	public User update(User updatedUser) {
		Assert.notNull(updatedUser, "user must not be null");

		checkUserExists(updatedUser);
		return userRepository.save(updatedUser);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		Assert.notNull(id, "id must not be null");

		return userRepository.findOne(id);
	}

	public List<User> findByEmail(String eMail) {
		Assert.notNull(eMail, "email must not be null");

		return userRepository.findByEmail(eMail);
	}

	private void checkUserExists(User updatedUser) {
		userRepository.findOne(updatedUser.getId());
	}

}
