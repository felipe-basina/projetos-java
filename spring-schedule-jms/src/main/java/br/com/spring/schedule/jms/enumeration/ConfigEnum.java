package br.com.spring.schedule.jms.enumeration;

/**
 * The Enum ConfigEnum.
 */
public enum ConfigEnum {

	/** The total receiver limit. */
	TOTAL_RECEIVER_LIMIT,

	/** The total max retry login. */
	TOTAL_MAX_RETRY_LOGIN,

	/** The mse url host. */
	MSE_URL_HOST,

	/** The mse default profile receipt. */
	MSE_DEFAULT_PROFILE_RECEIPT,

	/** The mse default pwd receipt. */
	MSE_DEFAULT_PWD_RECEIPT,

	/** The mse default profile n receipt. */
	MSE_DEFAULT_PROFILE_N_RECEIPT,

	/** The mse default pwd n receipt. */
	MSE_DEFAULT_PWD_N_RECEIPT,

	/** The mse encode. */
	MSE_ENCODE,

	/** The torpedo equipe la. */
	TORPEDO_EQUIPE_LA,

	/** The retry msg delivery. */
	RETRY_MSG_DELIVERY,

	/** The retry msg charge. */
	RETRY_MSG_CHARGE,

	/** The send sms mse delay. */
	SEND_SMS_MSE_DELAY,

	/** The charge message delay. */
	CHARGE_MESSAGE_DELAY,

	/** The mse ctn info profile. */
	MSE_CTN_INFO_PROFILE,

	/** The mse ctn info pwd. */
	MSE_CTN_INFO_PWD,

	/** The password exp days. */
	PASSWORD_EXP_DAYS,

	/** The mse status code cc ok success. */
	MSE_STATUS_CODE_CC_OK_SUCCESS,

	/** The mse status code cc api msisdn not found. */
	MSE_STATUS_CODE_CC_API_MSISDN_NOT_FOUND,

	/** The mse status code cc api advertisement denied. */
	MSE_STATUS_CODE_CC_API_ADVERTISEMENT_DENIED,

	/** The queue name send sms. */
	QUEUE_NAME_SEND_SMS,

	/** The queue name update. */
	QUEUE_NAME_UPDATE,

	/** The queue name charge. */
	QUEUE_NAME_CHARGE,

	/** The queue name receipt. */
	QUEUE_NAME_RECEIPT,

	/** The queue name register user. */
	QUEUE_NAME_REGISTER_USER,

	/** The mse charge profile. */
	MSE_CHARGE_PROFILE,

	/** The mse charge pwd. */
	MSE_CHARGE_PWD,

	/** The mse default share code. */
	MSE_DEFAULT_SHARE_CODE,

	/** The retry mse status. */
	RETRY_MSE_STATUS,

	/** The status mse delay. */
	STATUS_MSE_DELAY,

	/** The jms connection factory. */
	JMS_CONNECTION_FACTORY,

	/** The loader message delay. */
	LOADER_MESSAGE_DELAY,

	/** The update status delay. */
	UPDATE_STATUS_DELAY,

	/** The jms sendsms consumers. */
	TOTAL_MSGS_SEND_SMS_QUEUE,

	/** The jms updatemessage consumers. */
	TOTAL_MSGS_UPDATE_QUEUE,

	/** The total min msgs update queue. */
	TOTAL_MIN_MSGS_UPDATE_QUEUE,

	/** The jms receiptmessage consumers. */
	TOTAL_MSGS_RECEIPT_QUEUE,

	/** The jms chargemessage consumers. */
	TOTAL_MSGS_CHARGE_QUEUE,

	/** The total msgs register user. */
	TOTAL_MSGS_REGISTER_USER,

	/** The max size file. */
	MAX_SIZE_FILE,

	/** The receipt pending message delay. */
	RECEIPT_PENDING_MESSAGE_DELAY,

	/** The total loader register. */
	TOTAL_LOADER_REGISTER,

	/** The update status offer retry. */
	UPDATE_STATUS_OFFER_RETRY,

	/** The update status offer delay. */
	UPDATE_STATUS_OFFER_DELAY,

	/** The ddds list. */
	DDDS_LIST,

	/** The total msgs limit. */
	TOTAL_MSGS_LIMIT,

	/** The total appointments for search. */
	TOTAL_APPOINTMENTS_FOR_SEARCH,
	
	/** The ddds list nine digit. */
	DDDS_LIST_9DIGIT,

	/** The digits list nine digit. */
	DIGIT_LIST_9DIGIT;

}
