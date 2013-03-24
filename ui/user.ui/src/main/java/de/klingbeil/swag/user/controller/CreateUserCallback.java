package de.klingbeil.swag.user.controller;

import de.klingbeil.swag.user.model.UserViewModel;

public interface CreateUserCallback {
	void createUser(UserViewModel user);
}
