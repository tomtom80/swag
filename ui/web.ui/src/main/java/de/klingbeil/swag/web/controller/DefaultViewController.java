package de.klingbeil.swag.web.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.UserListViewController;

@Component
public class DefaultViewController implements Controller {

	@Resource
	ViewManager viewManager;

	@Resource
	UserListViewController controller;

	@PostConstruct
	void initView() {
		viewManager.setContentView(controller.getView());
	}

	@Override
	public View getView() {
		return viewManager.getContentView();
	}

}
