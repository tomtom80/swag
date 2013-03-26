package de.klingbeil.swag.core.controller;

import de.klingbeil.swag.core.view.View;

public interface ViewManager {

	/**
	 * Updates the content area with a new view.
	 */
	void setContentView(View contentView);

	/**
	 * Returns the application content view.
	 */
	View getContentView();
}
