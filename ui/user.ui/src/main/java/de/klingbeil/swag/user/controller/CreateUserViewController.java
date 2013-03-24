package de.klingbeil.swag.user.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.CreateUserView;

@Component
public class CreateUserViewController implements Controller {

	@Resource
	CreateUserView view;

	@Resource
	UserService userService;

	@PostConstruct
	void initView() {
		view.setCreateUserCallback(createCreateUserCallback());
	}

	@Override
	public View getView() {
		return view;
	}

	private CreateUserCallback createCreateUserCallback() {
		return new CreateUserCallback() {
			@Override
			public void createUser(UserViewModel user) {
				userService.create(user.toUser());
			}
		};
	}

}
