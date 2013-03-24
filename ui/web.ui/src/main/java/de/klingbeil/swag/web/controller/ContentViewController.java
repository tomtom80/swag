package de.klingbeil.swag.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.view.View;
import de.klingbeil.swag.user.controller.CreateUserViewController;

@Component
public class ContentViewController implements Controller {

	@Resource
	CreateUserViewController createUserViewController;

	@Override
	public View getView() {
		return createUserViewController.getView();
	}

}
