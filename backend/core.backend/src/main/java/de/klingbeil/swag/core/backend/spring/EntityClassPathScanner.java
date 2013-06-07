package de.klingbeil.swag.core.backend.spring;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

public class EntityClassPathScanner implements ResourceLoaderAware {
  
  private static final Logger LOG = LoggerFactory.getLogger( EntityClassPathScanner.class );
  
  private final TypeFilter annotationFilter;
  private final Collection<String> entityClassNames;
  
  private List<String> packages;
  private ResourceLoader resourceLoader;
  private ResourcePatternResolver resolver;
  private MetadataReaderFactory metadataReaderFactory;

  public EntityClassPathScanner() {
    annotationFilter = new AnnotationTypeFilter( Entity.class );
    entityClassNames = new HashSet<String>();
  }
  
  public void setPackages( List<String> packages ) {
    this.packages = packages;
  }

  @Override
  public void setResourceLoader( ResourceLoader resourceLoader ) {
    this.resourceLoader = resourceLoader;
  }

  @PostConstruct
  public void postConstruct() {
    LOG.info( "Start scanning classpath {} for JPA entities.", packages );
    resolver = ResourcePatternUtils.getResourcePatternResolver( resourceLoader );
    metadataReaderFactory = new CachingMetadataReaderFactory( resourceLoader );
    scanForEntities();
    LOG.info( "Finished scanning classpath.");
  }

  public Collection<String> getScannedClasses() {
    return entityClassNames;
  }

  private void scanForEntities() {
    if( packages != null ) {
      for( String packageName : packages ) {
        scanResources( findClassResources( packageName ) );
      }
    }
  }

  private void scanResources( Resource[] resources ) {
    for( Resource resource : resources ) {
      if( hasEntityAnnotation( resource ) ) {
        String className = getMetadataReader( resource ).getClassMetadata().getClassName();
        entityClassNames.add( className );
      }
    }
  }

  private boolean hasEntityAnnotation( Resource resource ) {
    try {
      return annotationFilter.match( getMetadataReader( resource ), metadataReaderFactory );
    } catch( IOException e ) {
      throw new RuntimeException( e );
    }
  }

  private MetadataReader getMetadataReader( Resource resource ) {
    try {
      return metadataReaderFactory.getMetadataReader( resource );
    } catch( IOException e ) {
      throw new RuntimeException( e );
    }
  }

  private Resource[] findClassResources( String packageName ) {
    try {
      return resolver.getResources( getPackageSearchPath( packageName ) );
    } catch( IOException e ) {
      throw new RuntimeException( e );
    }
  }

  private static String getPackageSearchPath( String packageName ) {
    return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
           + ClassUtils.convertClassNameToResourcePath( SystemPropertyUtils.resolvePlaceholders( packageName ) )
           + "/**/*.class";
  }
}
