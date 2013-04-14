package de.klingbeil.swag.core.controller;

import com.vaadin.server.Page.UriFragmentChangedListener;

import de.klingbeil.swag.core.view.View;

public interface Controller extends UriFragmentChangedListener {
	View getView();
}
