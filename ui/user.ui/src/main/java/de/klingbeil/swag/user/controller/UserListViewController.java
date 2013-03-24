package de.klingbeil.swag.user.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserListViewModel;
import de.klingbeil.swag.user.view.UserListView;

public class UserListViewController implements Controller {

	@Resource
	UserListView view;

	@Resource
	UserService userService;

	@Override
	public View getView() {
		return view;
	}

	@PostConstruct
	void initView() {
		List<User> userList = userService.findAll();
		view.setViewModel(UserListViewModel.from(userList));
	}
}
