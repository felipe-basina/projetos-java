package spring.jpa.eclipselink.utils;

/**
 * The Enum StatusProcessingType.
 */
public enum ProcessStatusEnum {

	/** The scheduled. */
	AGENDADO("1"),

	/** The processing. */
	PROCESSANDO("2"),

	/** The processed. */
	PROCESSADO("3"),

	/** Error invalid msisdn. */
	ERRO_MSISDN_INVALIDO("4"),

	/** Error processing advertising. */
	ERRO_PROCESSAMENTO_PUBLICIDADE("5"),

	/** Error processing. */
	ERRO_PROCESSAMENTO("6"),

	/** Error sending message. */
	ERRO_ENVIO_MENSAGEM("7"),

	/** The not processed. */
	NAO_PROCESSADO("8"),

	/** The waiting receipt. */
	AGUARDANDO_RECIBO("9"),

	/** The cancelled. */
	CANCELADO("10"),

	/** The erro tarifacao. */
	ERRO_TARIFACAO("11"),

	/** The mensagem tarifada. */
	MENSAGEM_TARIFADA("12"),

	/** DELIVRD. */
	MENSAGEM_ENTREGUE("13"),

	/** EXPIRED. */
	MENSAGEM_EXPIROU("14"),

	/** UNDELIV. */
	MENSAGEM_NAO_ENTREGUE("15"),

	/** UNKNOWN. */
	MENSAGEM_NAO_ENTREGUE_MOTIVO_DESCONHECIDO("16"),

	/** REJECTD. */
	MENSAGEM_REJEITADA("17");

	/** The id. */
	private String id;

	/**
	 * Instantiates a new process status enum.
	 *
	 * @param pId
	 *            the id
	 */
	private ProcessStatusEnum(final String pId) {
		this.id = pId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final String getId() {
		return this.id;
	}

}
