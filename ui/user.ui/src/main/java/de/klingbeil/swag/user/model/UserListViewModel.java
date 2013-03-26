package de.klingbeil.swag.user.model;

import java.util.LinkedList;
import java.util.List;

import de.klingbeil.swag.user.backend.model.User;

public class UserListViewModel {

	public static List<UserViewModel> from(List<User> userList) {
		List<UserViewModel> result = new LinkedList<UserViewModel>();
		for (User user : userList) {
			result.add(toUserViewModel(user));
		}
		return result;
	}

	private static UserViewModel toUserViewModel(User user) {
		UserViewModel result = new UserViewModel();
		result.setId(user.getId());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setEmail(user.getEmail());
		return result;
	}

}
