package de.klingbeil.swag.user.controller.internal;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.server.Page.UriFragmentChangedEvent;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.backend.model.User;
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.model.UserListViewModel;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.UserListView;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserListViewController implements Controller {

	private static final long serialVersionUID = 1L;

	public static final String URI_FRAGMENT = "userList";

	@Resource
	UserListView view;

	@Resource
	UserService userService;

	@Resource
	ViewManager viewManager;

	@Resource
	UriFragmentNavigator navigator;

	@Override
	public View getView() {
		updateViewModel();
		return view;
	}

	@Override
	public void uriFragmentChanged(UriFragmentChangedEvent event) {
		if (event.getUriFragment().equals(URI_FRAGMENT)) {
			viewManager.setContentView(getView());
		}
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
				navigator.navigateTo(CreateUserViewController.URI_FRAGMENT);
			}
		};
	}
}
