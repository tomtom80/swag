package de.klingbeil.swag.core.navigator.internal;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedListener;

import de.klingbeil.swag.core.backend.spring.ApplicationContextProvider;
import de.klingbeil.swag.core.controller.Controller;
import de.klingbeil.swag.core.navigator.UriFragmentNavigator;

@Component
public class UriFragmentNavigatorImpl implements UriFragmentNavigator {

	private static final Logger logger = LoggerFactory
			.getLogger(UriFragmentNavigatorImpl.class);

	@Override
	public void navigateTo(String uriFragment) {
		Page.getCurrent().setUriFragment(uriFragment);
	}

	@Override
	public void register(UriFragmentChangedListener controller) {
		logger.debug("controller registered: "
				+ controller.getClass().toString());
		Page.getCurrent().addUriFragmentChangedListener(controller);
	}

	@Override
	public void autoRegisterAllControllers() {
		for (Controller controller : getRegisteredControllers()) {
			register(controller);
		}
	}

	private static Collection<Controller> getRegisteredControllers() {
		return getApplicationContext().getBeansOfType(Controller.class)
				.values();
	}

	private static ApplicationContext getApplicationContext() {
		return ApplicationContextProvider.getApplicationContext();
	}

}
