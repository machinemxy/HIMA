package com.mxy.service.base;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class ServiceBase {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Properties appProperties;

	protected String getMessage(String messageId, Object ... params) {
		return messageSource.getMessage(messageId, params, Locale.JAPANESE);
	}

	protected String getProperty(String key) {
		return appProperties.getProperty(key);
	}
}
