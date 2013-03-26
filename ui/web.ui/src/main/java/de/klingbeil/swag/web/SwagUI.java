package de.klingbeil.swag.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import de.klingbeil.swag.web.controller.DefaultViewController;

@Component
@Scope("prototype")
public class SwagUI extends UI {

	private static final long serialVersionUID = 1L;

	@Resource
	DefaultViewController defaultViewController;

	@Override
	protected void init(VaadinRequest request) {
		setContent(defaultViewController.getView().getComponent());
	}

}
