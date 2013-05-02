package de.klingbeil.swag.user.controller.internal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vaadin.server.Page.UriFragmentChangedEvent;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.LoginCallback;
import de.klingbeil.swag.user.model.UserViewModel;
import de.klingbeil.swag.user.view.LoginView;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginViewController implements Controller {

	public static final String URI_FRAGMENT = "login";

	private static final long serialVersionUID = 1L;

	@Resource
	LoginView view;

	@Resource
	ViewManager viewManager;

	@Resource
	UriFragmentNavigator navigator;

	@Resource(name = "authenticationManager")
	AuthenticationManager authenticationManager;

	@PostConstruct
	void initView() {
		view.setLoginButtonCallback(createLoginCallback());
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

	private LoginCallback createLoginCallback() {
		return new LoginCallback() {
			@Override
			public void login(UserViewModel user) {
				try {
					Authentication authenticate = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(
									user.getUsername(), user.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(
							authenticate);
					navigator.navigateTo(UserListViewController.URI_FRAGMENT);

				} catch (AuthenticationException e) {
					view.setComponentError(e.getMessage());
				}
			}
		};
	}

}
