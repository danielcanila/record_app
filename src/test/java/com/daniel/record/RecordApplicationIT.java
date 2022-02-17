package com.daniel.record;

import com.daniel.record.repository.AuditRepository;
import com.daniel.record.repository.RecordRepository;
import com.daniel.record.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RecordApplicationIT {

	private static final String VALID_PAYLOAD =
			"{\"RecordType\":\"record_type_value\",\"DeviceId\":\"device_id_value\",\"EventDateTime\":\"2014-05-12T05:09:48Z\",\"FieldA\":99,\"FieldB\":\"field_b_value\",\"FieldC\":123.45}";
	private static final String EMPTY_PAYLOAD = "{}";
	private static final String HOST = "http://localhost:8080";
	private static final String BASE_URL = "/v1/record";

	@Value("${basic.security.token}")
	private String securityToken;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private RecordRepository recordRepository;
	@Autowired
	private AuditRepository auditRepository;

	@Test
	void noContent_success() throws Exception {
		// given
		var noContentPath = "/nocontent";
		var securityToken = this.securityToken;
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isNoContent();

		// when
		var response = performRequest(noContentPath, securityToken, validPayload, expectedStatus);

		// then
		assertTrue(StringUtils.isEmpty(response));
		assertEquals(1, recordRepository.count());
		assertEquals(1, auditRepository.count());
	}

	@Test
	void noContent_securityFail() throws Exception {
		// given
		var noContentPath = "/nocontent";
		var wrongToken = "wrongToken";
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isUnauthorized();

		// when
		var response = performRequest(noContentPath, wrongToken, validPayload, expectedStatus);

		// then
		assertEquals(Constants.INVALID_TOKEN_MESSAGE, response);
		assertEquals(0, recordRepository.count());
		assertEquals(0, auditRepository.count());
	}

	@Test
	void noContent_emptyPayload() throws Exception {
		// given
		var noContentPath = "/nocontent";
		var securityToken = this.securityToken;
		var emptyPayload = EMPTY_PAYLOAD;
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(noContentPath, securityToken, emptyPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMissingFieldsMessage(response);
	}

	@Test
	void noContent_invalidPayloadFields() throws Exception {
		// given
		var noContentPath = "/nocontent";
		var securityToken = this.securityToken;
		var invalidPayload = buildInvalidJson();
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(noContentPath, securityToken, invalidPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMaxSizeErrorMessage(response);
	}

	@Test
	void echo_success() throws Exception {
		// given
		var echoPath = "/echo";
		var securityToken = this.securityToken;
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isOk();

		// when
		var response = performRequest(echoPath, securityToken, validPayload, expectedStatus);

		// then
		assertEquals(validPayload, response);
		assertEquals(1, recordRepository.count());
		assertEquals(1, auditRepository.count());
	}

	@Test
	void echo_securityFail() throws Exception {
		// given
		var echoPath = "/echo";
		var wrongToken = "wrongToken";
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isUnauthorized();

		// when
		var response = performRequest(echoPath, wrongToken, validPayload, expectedStatus);

		// then
		assertEquals(Constants.INVALID_TOKEN_MESSAGE, response);
		assertEquals(0, recordRepository.count());
		assertEquals(0, auditRepository.count());
	}

	@Test
	void echo_emptyPayload() throws Exception {
		// given
		var echoPath = "/echo";
		var securityToken = this.securityToken;
		var emptyPayload = EMPTY_PAYLOAD;
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(echoPath, securityToken, emptyPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMissingFieldsMessage(response);
	}

	@Test
	void echo_invalidPayloadFields() throws Exception {
		// given
		var echoPath = "/echo";
		var securityToken = this.securityToken;
		var invalidPayload = buildInvalidJson();
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(echoPath, securityToken, invalidPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMaxSizeErrorMessage(response);
	}

	@Test
	void device_success() throws Exception {
		// given
		var devicePath = "/device";
		var securityToken = this.securityToken;
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isOk();

		// when
		var response = performRequest(devicePath, securityToken, validPayload, expectedStatus);

		// then
		assertEquals("device_id_value", response);
		assertEquals(1, recordRepository.count());
		assertEquals(1, auditRepository.count());
	}

	@Test
	void device_securityFail() throws Exception {
		// given
		var devicePath = "/device";
		var wrongToken = "wrongToken";
		var validPayload = VALID_PAYLOAD;
		var expectedStatus = status().isUnauthorized();

		// when
		var response = performRequest(devicePath, wrongToken, validPayload, expectedStatus);

		// then
		assertEquals(Constants.INVALID_TOKEN_MESSAGE, response);
		assertEquals(0, recordRepository.count());
		assertEquals(0, auditRepository.count());
	}

	@Test
	void device_emptyPayload() throws Exception {
		// given
		var devicePath = "/device";
		var securityToken = this.securityToken;
		var emptyPayload = EMPTY_PAYLOAD;
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(devicePath, securityToken, emptyPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMissingFieldsMessage(response);
	}

	@Test
	void device_invalidPayloadFields() throws Exception {
		// given
		var devicePath = "/device";
		var securityToken = this.securityToken;
		var invalidPayload = buildInvalidJson();
		var expectedStatus = status().isBadRequest();

		// when
		var response = performRequest(devicePath, securityToken, invalidPayload, expectedStatus);

		// then
		assertEquals(0, recordRepository.count());
		assertEquals(1, auditRepository.count());
		checkForMaxSizeErrorMessage(response);
	}

	private String performRequest(String path,
	                              String securityToken,
	                              String payload,
	                              ResultMatcher expectedStatusCode) throws Exception {
		return mockMvc
				       .perform(
						       MockMvcRequestBuilders
								       .post(HOST + BASE_URL + path)
								       .servletPath(BASE_URL + path)
								       .contentType(MediaType.APPLICATION_JSON)
								       .accept(MediaType.APPLICATION_JSON)
								       .content(payload)
								       .header("Authorization", securityToken))
				       .andExpect(expectedStatusCode)
				       .andReturn()
				       .getResponse()
				       .getContentAsString();
	}


	private String buildInvalidJson() {
		var invalidRecordType = StringUtils.repeat("R", Constants.RECORD_TYPE_MAX_SIZE + 1);
		var invalidDeviceId = StringUtils.repeat("D", Constants.DEVICE_ID_MAX_SIZE + 1);
		var invalidFieldB = StringUtils.repeat("F", Constants.FIELD_MAX_SIZE + 1);
		return VALID_PAYLOAD.replace("record_type_value", invalidRecordType)
				       .replace("device_id_value", invalidDeviceId)
				       .replace("field_b_value", invalidFieldB);
	}

	private void checkForMissingFieldsMessage(String response) {
		assertTrue(response.contains(Constants.RECORD_TYPE_IS_REQUIRED));
		assertTrue(response.contains(Constants.DEVICE_ID_IS_REQUIRED));
		assertTrue(response.contains(Constants.EVENT_DATE_TIME_IS_REQUIRED));
		assertTrue(response.contains(Constants.FIELD_A_IS_REQUIRED));
		assertTrue(response.contains(Constants.FIELD_B_IS_REQUIRED));
		assertTrue(response.contains(Constants.FIELD_C_IS_REQUIRED));
	}


	private void checkForMaxSizeErrorMessage(String response) {
		assertTrue(response.contains(Constants.RECORD_TYPE_MAX_SIZE_MESSAGE));
		assertTrue(response.contains(Constants.DEVICE_ID_MAX_SIZE_MESSAGE));
		assertTrue(response.contains(Constants.FIELD_B_MAX_SIZE_MESSAGE));
	}
}
