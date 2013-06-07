package de.klingbeil.swag.core.backend.spring;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class LoggingPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

  private static final String PASSWORD_REPLACEMENT_STRING = "xxxxxxxxx";
  private static final Logger LOG = LoggerFactory.getLogger( LoggingPropertyPlaceholderConfigurer.class );
  private Map<Object, Object> sortedPropertyMap;
  
  public String getProperty( String key ) {
    Object value = sortedPropertyMap.get( key );
    return value != null ? value.toString() : null;
  }

  @Override
  protected void processProperties( ConfigurableListableBeanFactory beanFactoryToProcess,
                                    Properties props ) throws BeansException
  {
    super.processProperties( beanFactoryToProcess, props );
    sortedPropertyMap = new TreeMap<Object, Object>( props );
    logAllProperties();
  }

  private void logAllProperties() {
    for( Entry<Object, Object> entry : sortedPropertyMap.entrySet() ) {
      String name = ( String )entry.getKey();
      Object value = entry.getValue();
      logProperty( name, value );
    }
  }

  private static void logProperty( String name, Object value ) {
    LOG.info( "Spring property: {}={}", name, convertValueIfNecessary( name, value ) );
  }

  private static Object convertValueIfNecessary( String name, Object value ) {
    Object logValue = value;
    if( name.toLowerCase().contains( "password" ) ) {
      logValue = PASSWORD_REPLACEMENT_STRING;
    }
    return logValue;
  }
}
