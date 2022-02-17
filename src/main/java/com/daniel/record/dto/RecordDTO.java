package com.daniel.record.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.daniel.record.util.Constants.DEVICE_ID_IS_REQUIRED;
import static com.daniel.record.util.Constants.DEVICE_ID_MAX_SIZE;
import static com.daniel.record.util.Constants.DEVICE_ID_MAX_SIZE_MESSAGE;
import static com.daniel.record.util.Constants.EVENT_DATE_TIME_IS_REQUIRED;
import static com.daniel.record.util.Constants.FIELD_A_IS_REQUIRED;
import static com.daniel.record.util.Constants.FIELD_B_IS_REQUIRED;
import static com.daniel.record.util.Constants.FIELD_B_MAX_SIZE_MESSAGE;
import static com.daniel.record.util.Constants.FIELD_C_IS_REQUIRED;
import static com.daniel.record.util.Constants.FIELD_MAX_SIZE;
import static com.daniel.record.util.Constants.RECORD_TYPE_IS_REQUIRED;
import static com.daniel.record.util.Constants.RECORD_TYPE_MAX_SIZE;
import static com.daniel.record.util.Constants.RECORD_TYPE_MAX_SIZE_MESSAGE;

@Data
@Builder
public class RecordDTO {

	@NotEmpty(message = RECORD_TYPE_IS_REQUIRED)
	@Size(max = RECORD_TYPE_MAX_SIZE, message = RECORD_TYPE_MAX_SIZE_MESSAGE)
	@JsonProperty("RecordType")
	private String recordType;

	@NotEmpty(message = DEVICE_ID_IS_REQUIRED)
	@Size(max = DEVICE_ID_MAX_SIZE, message = DEVICE_ID_MAX_SIZE_MESSAGE)
	@JsonProperty("DeviceId")
	private String deviceId;

	@NotNull(message = EVENT_DATE_TIME_IS_REQUIRED)
	@JsonProperty("EventDateTime")
	private ZonedDateTime eventDateTime;

	@NotNull(message = FIELD_A_IS_REQUIRED)
	@JsonProperty("FieldA")
	private Long fieldA;

	@NotEmpty(message = FIELD_B_IS_REQUIRED)
	@Size(max = FIELD_MAX_SIZE, message = FIELD_B_MAX_SIZE_MESSAGE)
	@JsonProperty("FieldB")
	private String fieldB;

	@NotNull(message = FIELD_C_IS_REQUIRED)
	@JsonProperty("FieldC")
	private Double fieldC;

}
