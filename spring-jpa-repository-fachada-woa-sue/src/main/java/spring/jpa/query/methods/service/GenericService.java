package spring.jpa.query.methods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import spring.jpa.query.methods.model.Message;

public abstract class GenericService {

	@Autowired
	private MessageSource message;

	protected Message getMessage(String name, String... paramKeys) {

		String[] params = new String[paramKeys.length];

		for (int i = 0; i < params.length; i++) {
			if (paramKeys[i].startsWith("#")) {
				params[i] = paramKeys[i].substring(1);
			} else {
				params[i] = message.getMessage(paramKeys[i], null,
						LocaleContextHolder.getLocale());
			}
		}

		return new Message(name, message.getMessage(name, params,
				LocaleContextHolder.getLocale()));
	}
}