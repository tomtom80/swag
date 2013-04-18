package de.klingbeil.swag.core.controller.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vaadin.ui.Component;

import de.klingbeil.swag.core.view.MainLayout;
import de.klingbeil.swag.core.view.View;

public class ViewManagerImplTest {

	private ViewManagerImpl viewManager;

	@Mock
	private MainLayout mainlayout;
	@Mock
	private Component viewComponent;

	class TestView implements View {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getComponent() {
			return viewComponent;
		}

		@Override
		public String getCaption() {

			return "testView";
		}
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		viewManager = new ViewManagerImpl();
		viewManager.mainLayout = mainlayout;
	}

	@Test
	public void testContentView() {
		TestView view = new TestView();

		viewManager.setContentView(view);

		verify(mainlayout).setContent(view);
		assertSame(mainlayout, viewManager.getContentView());
	}
}
