package spring.jpa.eclipselink.utils;

/**
 * The Enum CompanyEnum.
 */
public enum CompanyEnum {

	/** The claro. */
	CLARO(1L),

	/** The claro destinatario. */
	CLARO_DESTINATARIO(2L),

	/** The claro padrao. */
	CLARO_PADRAO(3L);

	/** The status code. */
	private Long id;

	/**
	 * Instantiates a new company enum.
	 *
	 * @param pId
	 *            the id
	 */
	private CompanyEnum(final Long pId) {
		this.id = pId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final Long getId() {
		return this.id;
	}
}