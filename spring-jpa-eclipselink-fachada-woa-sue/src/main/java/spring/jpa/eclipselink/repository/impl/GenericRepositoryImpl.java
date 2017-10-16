package spring.jpa.eclipselink.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.eclipselink.domain.Message;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.GenericRepository;

@Repository("genericRepositoryImpl")
@Transactional
public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@PersistenceContext(unitName = "SUECore_Unit")
	protected EntityManager em;

	@Autowired
	private MessageSource messages;

	private Class<T> type;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericRepositoryImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	@Transactional(readOnly = true)
	public T create(T t) throws DataException {
		this.em.persist(t);
		return t;
	}

	@Transactional(readOnly = true)
	public void delete(Object id) throws DataException {
		this.em.remove(this.em.getReference(type, id));
	}

	@Transactional(readOnly = true)
	public T find(Object id) throws DataException {
		return (T) this.em.find(type, id);
	}

	@Transactional(readOnly = true)
	public T update(T t) throws DataException {
		return this.em.merge(t);
	}

	public Message getMessage(String name, String... paramKeys) {

		String[] params = new String[paramKeys.length];

		for (int i = 0; i < params.length; i++) {
			if (paramKeys[i].startsWith("#")) {
				params[i] = paramKeys[i].substring(1);
			} else {
				params[i] = messages.getMessage(paramKeys[i], null,
						LocaleContextHolder.getLocale());
			}
		}

		return new Message(name, messages.getMessage(name, params,
				LocaleContextHolder.getLocale()));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> getAll() throws DataException {
		String primeiraLetra = type.getSimpleName().substring(0, 1)
				.toLowerCase();
		String nomeRestante = type.getSimpleName().substring(1,
				type.getSimpleName().length());

		StringBuilder sQuery = new StringBuilder("select g from "
				.concat(primeiraLetra).concat(nomeRestante).concat(" g "));

		Query query = this.getEntityManager().createQuery(sQuery.toString());
		return query.getResultList();
	}

	public Class<T> getType() {
		return type;
	}

	public void logException(Throwable throwable) {

		String newLine = System.getProperty("line.separator");

		StringBuilder logMessage = new StringBuilder();
		logMessage.append('\t');
		logMessage.append("Exception:         ");
		logMessage.append(throwable.getClass().getCanonicalName());
		logMessage.append(newLine);
		logMessage.append('\t');
		logMessage.append("Exception Message: ");
		logMessage.append(throwable.getMessage());
		logMessage.append(newLine);
		logMessage.append('\t');
		logMessage.append("Throwable: ");
		logMessage.append(ExceptionUtils.getFullStackTrace(throwable));

		logger.error(logMessage.toString());
	}
}
