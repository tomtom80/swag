package de.klingbeil.swag.core.controller.internal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.view.MainLayout;
import de.klingbeil.swag.core.view.View;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ViewManagerImpl implements ViewManager {

	private static final long serialVersionUID = 1L;

	@Resource
	MainLayout mainLayout;

	@Override
	public void setContentView(View contentView) {
		this.mainLayout.setContent(contentView);
	}

	@Override
	public View getContentView() {
		return mainLayout;
	}

}
