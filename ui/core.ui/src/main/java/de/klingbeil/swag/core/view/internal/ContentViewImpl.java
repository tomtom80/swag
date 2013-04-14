package de.klingbeil.swag.core.view.internal;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import de.klingbeil.swag.core.view.ContentView;
import de.klingbeil.swag.core.view.View;

@org.springframework.stereotype.Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ContentViewImpl implements ContentView {

	private Layout component;
	private View lastContentView;

	public ContentViewImpl() {
		initComponet();
	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setContent(View contentView) {
		if (lastContentView != null) {
			component.removeComponent(lastContentView.getComponent());
		}
		lastContentView = contentView;
		component.addComponent(contentView.getComponent());
		component.markAsDirty();
	}

	private void initComponet() {
		component = createMainComponent();
	}

	private VerticalLayout createMainComponent() {
		return new VerticalLayout();
	}

}
