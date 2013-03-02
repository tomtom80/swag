package de.klingbeil.swag.user.backend.spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.klingbeil.swag.user.config.context.ApplicationContextBackend;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextBackend.class})
public class ApplicationContextProviderTest {

	@Test
	public void testEnsureApplicationContextIsInjected() {
		assertNotNull(ApplicationContextProvider.getApplicationContext());
	}

}
