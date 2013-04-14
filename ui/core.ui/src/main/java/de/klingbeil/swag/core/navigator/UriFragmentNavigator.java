package de.klingbeil.swag.core.navigator;

import com.vaadin.server.Page.UriFragmentChangedListener;

public interface UriFragmentNavigator {

	void autoRegisterAllControllers();

	void register(UriFragmentChangedListener listener);

	void navigateTo(String uriFragment);
}
