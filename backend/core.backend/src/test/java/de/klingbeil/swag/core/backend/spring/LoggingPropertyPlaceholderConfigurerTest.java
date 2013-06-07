package de.klingbeil.swag.core.backend.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.klingbeil.swag.core.config.context.ApplicationContextLoggingPropertyPlaceholderConfigurerTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes= { ApplicationContextLoggingPropertyPlaceholderConfigurerTest.class } )
public class LoggingPropertyPlaceholderConfigurerTest {
  
  @Resource
  private LoggingPropertyPlaceholderConfigurer configurer;
  
  @Test
  public void testGetProperties() {
    assertEquals( "testtest", configurer.getProperty( "test" ) );
    assertNull( configurer.getProperty( "not.existing.property" ) );
  }
}
