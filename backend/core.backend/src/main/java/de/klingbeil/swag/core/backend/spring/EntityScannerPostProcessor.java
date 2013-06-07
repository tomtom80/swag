package de.klingbeil.swag.core.backend.spring;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class EntityScannerPostProcessor implements PersistenceUnitPostProcessor{

  @Resource
  EntityClassPathScanner entityClassPathScanner;

  @Override
  public void postProcessPersistenceUnitInfo( MutablePersistenceUnitInfo pui ) {
    Collection<String> entityClasses = entityClassPathScanner.getScannedClasses();
    for( String className : entityClasses ) {
      pui.addManagedClassName( className );
    }
  }
}
