package de.klingbeil.swag.user.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserListViewModel;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.UserListView;

@Component
public class UserListViewController implements Controller {

	@Resource
	UserListView view;

	@Resource
	UserService userService;

	@Resource
	CreateUserViewController createUserViewController;

	@Resource
	ViewManager viewManager;

	@Override
	public View getView() {
		updateViewModel();
		return view;
	}

	@PostConstruct
	void initView() {
		view.setCreateUserButtonCallback(createNavigateToCreateUserViewCallback());
	}

	private void updateViewModel() {
		view.setViewModel(fetchUserModelList());
	}

	private List<UserViewModel> fetchUserModelList() {
		List<User> userList = userService.findAll();
		return UserListViewModel.from(userList);
	}

	private Runnable createNavigateToCreateUserViewCallback() {
		return new Runnable() {
			@Override
			public void run() {
				viewManager.setContentView(createUserViewController.getView());
			}
		};
	}
}
