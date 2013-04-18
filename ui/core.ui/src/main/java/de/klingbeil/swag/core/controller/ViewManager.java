package de.klingbeil.swag.core.controller;

import java.io.Serializable;

import de.klingbeil.swag.core.view.View;

public interface ViewManager extends Serializable {

	/**
	 * Updates the content area with a new view.
	 */
	void setContentView(View contentView);

	/**
	 * Returns the application content view.
	 */
	View getContentView();
}
