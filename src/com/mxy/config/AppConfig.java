package com.mxy.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean
	public PropertiesFactoryBean appProperties() {
		PropertiesFactoryBean appProperties = new PropertiesFactoryBean();
		appProperties.setLocation(new ClassPathResource("app.properties"));
		return appProperties;
	}
}
