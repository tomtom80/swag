package de.klingbeil.swag.core.backend.spring;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.klingbeil.swag.core.config.context.ApplicationContextCoreTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextCoreTest.class})
public class ApplicationContextProviderTest {

	@Test
	public void testEnsureApplicationContextIsInjected() {
		assertNotNull(ApplicationContextProvider.getApplicationContext());
	}

}
