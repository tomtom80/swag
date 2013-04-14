package de.klingbeil.swag.user.controller.internal;

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
import de.klingbeil.swag.user.backend.service.UserService;
import de.klingbeil.swag.user.controller.CreateUserCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.CreateUserView;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CreateUserViewController implements Controller {

	private static final long serialVersionUID = 1L;

	public static final String URI_FRAGMENT = "createUser";

	@Resource
	CreateUserView view;

	@Resource
	UserService userService;

	@Resource
	ViewManager viewManager;

	@Resource
	UriFragmentNavigator navigator;

	@PostConstruct
	void initView() {
		view.setSubmitButtonCallback(createCreateUserCallback());
	}

	@Override
	public View getView() {
		return view;
	}

	@Override
	public void uriFragmentChanged(UriFragmentChangedEvent event) {
		if (event.getUriFragment().equals(URI_FRAGMENT)) {
			viewManager.setContentView(getView());
		}
	}

	private CreateUserCallback createCreateUserCallback() {
		return new CreateUserCallback() {
			@Override
			public void createUser(UserViewModel user) {
				userService.create(user.toUser());
				redirectToUserListView();
			}

		};
	}

	private void redirectToUserListView() {
		navigator.navigateTo(UserListViewController.URI_FRAGMENT);
	}

}
