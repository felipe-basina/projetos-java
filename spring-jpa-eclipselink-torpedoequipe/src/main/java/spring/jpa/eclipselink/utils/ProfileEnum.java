package spring.jpa.eclipselink.utils;

/**
 * The Enum ProfileEnum.
 */
public enum ProfileEnum {

	/** Master. */
	MASTER(1L),

	/** Consultor Claro. */
	CONSULTOR_CLARO(2L),

	/** Destinat�rio. */
	DESTINATARIO(3L),

	/** Padr�o. */
	PADRAO(4L),

	/** Atendimento Claro. */
	ATENDIMENTO_CLARO(5L);

	/** The status code. */
	private Long id;

	/**
	 * Instantiates a new profile enum.
	 *
	 * @param pId
	 *            the id
	 */
	private ProfileEnum(final Long pId) {
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