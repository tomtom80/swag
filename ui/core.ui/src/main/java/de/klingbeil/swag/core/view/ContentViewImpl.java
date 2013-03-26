package de.klingbeil.swag.core.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
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
