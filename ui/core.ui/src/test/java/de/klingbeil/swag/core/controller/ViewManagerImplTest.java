package de.klingbeil.swag.core.controller;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.ui.Component;

import de.klingbeil.swag.core.view.ContentView;
import de.klingbeil.swag.core.view.View;

public class ViewManagerImplTest {

	private ViewManagerImpl viewManager;

	@Mock
	private ContentView contentView;
	@Mock
	private Component viewComponent;

	class TestView implements View {
		@Override
		public Component getComponent() {
			return viewComponent;
		}
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		viewManager = new ViewManagerImpl();
		viewManager.contentView = contentView;
	}

	@Test
	public void testContentView() {
		TestView view = new TestView();

		viewManager.setContentView(view);

		verify(contentView).setContent(view);
		assertSame(contentView, viewManager.getContentView());
	}
}
