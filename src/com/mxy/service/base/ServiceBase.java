package com.mxy.service.base;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class ServiceBase {
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String messageId, Object ... params) {
		return messageSource.getMessage(messageId, params, Locale.JAPANESE);
	}
}
