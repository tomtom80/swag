package de.klingbeil.swag.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import de.klingbeil.swag.web.controller.ContentViewController;

@Component
@Scope("prototype")
public class SwagUI extends UI {

	private static final long serialVersionUID = 1L;

	@Resource
	ContentViewController contentViewController;

	@Override
	protected void init(VaadinRequest request) {
		setContent(contentViewController.getView().getComponent());
	}

}
