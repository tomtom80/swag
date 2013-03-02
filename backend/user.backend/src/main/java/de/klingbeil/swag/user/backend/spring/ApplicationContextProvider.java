package de.klingbeil.swag.user.backend.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	private static final Logger LOG = LoggerFactory
			.getLogger(ApplicationContextProvider.class);
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext newContext)
			throws BeansException {
		if (applicationContext == null || newContext == null
				|| applicationContext.equals(newContext)) {
			applicationContext = newContext;
		} else {
			String msg = "Cannot set ApplicationContext because already set. If this happens during integration test execution, there is probably an integration test without the TestExecutionListener ApplicationContextHolderListener attached. See SpringDelegationListener for details.";
			LOG.error(msg);
		}
	}
}
