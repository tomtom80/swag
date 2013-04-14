package de.klingbeil.swag.core.controller.internal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.klingbeil.swag.core.controller.ViewManager;
import de.klingbeil.swag.core.view.ContentView;
import de.klingbeil.swag.core.view.View;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ViewManagerImpl implements ViewManager {

	@Resource
	ContentView contentView;

	@Override
	public void setContentView(View contentView) {
		this.contentView.setContent(contentView);
	}

	@Override
	public View getContentView() {
		return contentView;
	}

}
