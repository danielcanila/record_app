package com.daniel.record.util;

public class Constants {

	public static final String RECORD_TYPE_IS_REQUIRED = "RecordType is required.";
	public static final String DEVICE_ID_IS_REQUIRED = "DeviceId is required.";
	public static final String EVENT_DATE_TIME_IS_REQUIRED = "EventDateTime is required.";
	public static final String FIELD_A_IS_REQUIRED = "FieldA is required.";
	public static final String FIELD_B_IS_REQUIRED = "FieldB is required.";
	public static final String FIELD_C_IS_REQUIRED = "FieldC is required.";
	public static final int RECORD_TYPE_MAX_SIZE = 255;
	public static final int DEVICE_ID_MAX_SIZE = 255;
	public static final int FIELD_MAX_SIZE = 65535;
	public static final String RECORD_TYPE_MAX_SIZE_MESSAGE = "RecordType size should not exceed 255 characters.";
	public static final String DEVICE_ID_MAX_SIZE_MESSAGE = "DeviceId size should not exceed 255 characters.";
	public static final String FIELD_B_MAX_SIZE_MESSAGE = "FieldB size should not exceed 65535 characters.";
	public static final String INVALID_TOKEN_MESSAGE = "Invalid token.";
	public static final String MANDATORY_IP_AND_URL = "Request ip & url are mandatory for auditing.";
	public static final String RECORD_CANNOT_BE_NULL_MESSAGE = "RecordDTO cannot be null.";
}
