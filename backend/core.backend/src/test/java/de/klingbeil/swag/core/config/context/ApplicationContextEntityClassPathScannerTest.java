package de.klingbeil.swag.core.config.context;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import de.klingbeil.swag.core.backend.spring.EntityClassPathScanner;

@Configuration
@ComponentScan(basePackages = { "de.klingbeil.swag.core.backend" })
public class ApplicationContextEntityClassPathScannerTest {
	@Bean
	public EntityClassPathScanner entityClassPathScanner() {
		EntityClassPathScanner scanner = new EntityClassPathScanner();
		scanner.setPackages(Arrays.asList("de.klingbeil.swag.core.backend.entity"));
		return scanner;
	}
}
