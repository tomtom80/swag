package de.klingbeil.swag.user.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.UserView;

@Component
public class UserViewController implements Controller {

	@Resource
	UserView view;

	@Resource
	UserService userService;

	@PostConstruct
	void initView() {
		view.setCreateUserCallback(createCreateUserCallback());
	}

	private CreateUserCallback createCreateUserCallback() {
		return new CreateUserCallback() {
			@Override
			public void createUser(UserViewModel user) {
				userService.create(user.toUser());
			}
		};
	}

	@Override
	public View getView() {
		return view;
	}

}
