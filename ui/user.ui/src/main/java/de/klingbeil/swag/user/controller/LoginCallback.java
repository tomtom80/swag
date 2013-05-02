package de.klingbeil.swag.user.controller;

import de.klingbeil.swag.user.model.UserViewModel;

public interface LoginCallback {
	void login(UserViewModel user);
}
