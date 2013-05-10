package de.klingbeil.swag.core.navigator;

import java.io.Serializable;

import com.vaadin.server.Page.UriFragmentChangedListener;

public interface UriFragmentNavigator extends Serializable {

	void autoRegisterAllControllers();

	void register(UriFragmentChangedListener listener);

	void navigateTo(String uriFragment);
}
