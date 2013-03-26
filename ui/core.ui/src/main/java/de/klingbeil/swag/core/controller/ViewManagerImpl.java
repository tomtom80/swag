package de.klingbeil.swag.core.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.klingbeil.swag.core.view.ContentView;
import de.klingbeil.swag.core.view.View;

@Component
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
