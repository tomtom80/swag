package de.klingbeil.swag.core.view;

import java.io.Serializable;

import com.vaadin.ui.Component;

public interface View extends Serializable {
	Component getComponent();

	String getCaption();
}
