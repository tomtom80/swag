package de.klingbeil.swag.core.backend.spring;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;


public class EntityScannerPostProcessorTest {
  
  @Test
  public void testRegisteredScannedEntities() {
    String className = "testClass";
    EntityScannerPostProcessor processor = new EntityScannerPostProcessor();
    EntityClassPathScanner scanner = mock( EntityClassPathScanner.class );
    when( scanner.getScannedClasses() ).thenReturn( Collections.singletonList( className ) );
    processor.entityClassPathScanner = scanner;
    MutablePersistenceUnitInfo persistenceUnit = mock( MutablePersistenceUnitInfo.class );
    
    processor.postProcessPersistenceUnitInfo( persistenceUnit );
    
    verify( persistenceUnit ).addManagedClassName( className );
  }
}
