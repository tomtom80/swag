package de.klingbeil.swag.core.view.internal;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

import de.klingbeil.swag.core.view.MainLayout;
import de.klingbeil.swag.core.view.View;

@org.springframework.stereotype.Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainLayoutImpl implements MainLayout {

	private static final long serialVersionUID = 1L;
	private CssLayout headerComponent;
	private CssLayout sidebarComponent;
	private CssLayout contentComponent;

	private Layout rootComponent;
	private View lastContentView;
	private HorizontalLayout mainComponent;

	public MainLayoutImpl() {
		initComponet();
	}

	@Override
	public Component getComponent() {
		return rootComponent;
	}

	@Override
	public void setContent(View contentView) {
		if (lastContentView != null) {
			contentComponent.removeAllComponents();
		}
		lastContentView = contentView;
		contentComponent.addComponent(contentView.getComponent());
		contentComponent.markAsDirty();
	}

	private void initComponet() {
		rootComponent = createRootComponent();
		headerComponent = createHeaderComponent();
		mainComponent = createMainComponent();
		sidebarComponent = createSidebarComponent();
		contentComponent = createContentComponent();
		mainComponent.addComponent(sidebarComponent);
		mainComponent.addComponent(contentComponent);
		rootComponent.addComponent(headerComponent);
		rootComponent.addComponent(mainComponent);
	}

	private CssLayout createRootComponent() {
		CssLayout result = new CssLayout();
		result.addStyleName(MainLayout.STYLE_ROOT);
		return result;
	}

	private HorizontalLayout createMainComponent() {
		HorizontalLayout result = new HorizontalLayout();
		result.addStyleName(MainLayout.STYLE_MAIN);
		return result;
	}

	private CssLayout createHeaderComponent() {
		CssLayout result = new CssLayout();
		result.addStyleName(MainLayout.STYLE_HEADER);
		result.addComponent(new Image(null, new ThemeResource(
				"img/swag-logo.jpg")));
		return result;
	}

	private CssLayout createSidebarComponent() {
		CssLayout result = new CssLayout();
		result.addStyleName(MainLayout.STYLE_SIDEBAR);
		result.addComponent(new Label("Sidebar"));
		return result;
	}

	private CssLayout createContentComponent() {
		CssLayout result = new CssLayout();
		result.addStyleName(MainLayout.STYLE_CONTENT);
		return result;
	}

	@Override
	public String getCaption() {
		return "MainLayout";
	}

}
