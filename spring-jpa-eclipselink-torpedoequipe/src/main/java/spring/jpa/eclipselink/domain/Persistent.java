package spring.jpa.eclipselink.domain;

import java.io.Serializable;

/**
 * The Interface Persistent.
 * 
 * @param <ID>
 *            the generic type
 */
public interface Persistent<ID extends Serializable> extends Serializable {

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	ID getId();

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	void setId(ID id);

}
