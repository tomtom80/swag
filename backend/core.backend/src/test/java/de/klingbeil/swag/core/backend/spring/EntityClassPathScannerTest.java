package de.klingbeil.swag.core.backend.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.klingbeil.swag.core.backend.entity.DummyEntity;
import de.klingbeil.swag.core.config.context.ApplicationContextEntityClassPathScannerTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextEntityClassPathScannerTest.class)
public class EntityClassPathScannerTest {
  @Resource
  private EntityClassPathScanner scanner;

  @Test
  public void ensureTestEntityIsFound() {
    Collection<String> classes = scanner.getScannedClasses();
    
    assertNotNull( classes );
    assertEquals( 1, classes.size() );
    assertTrue( classes.contains( DummyEntity.class.getName() ) );
  }
  
  @Test
  public void testIfPackagesAreNotConfigured() {
    EntityClassPathScanner localScanner = new EntityClassPathScanner();
    localScanner.setPackages( null );
    localScanner.setResourceLoader( mock( ResourceLoader.class ) );
    
    localScanner.postConstruct();
    
    assertTrue( localScanner.getScannedClasses().isEmpty() );
  }
}
