package de.klingbeil.swag.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;
import de.klingbeil.swag.user.controller.internal.UserListViewController;

@Theme("swag")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SwagUI extends UI {

	private static final long serialVersionUID = 1L;

	@Resource
	UriFragmentNavigator navigator;

	@Resource
	ViewManager viewManager;

	@Override
	protected void init(VaadinRequest request) {
		navigator.autoRegisterAllControllers();
		setContent(viewManager.getContentView().getComponent());
		navigator.navigateTo(UserListViewController.URI_FRAGMENT);
	}
}
