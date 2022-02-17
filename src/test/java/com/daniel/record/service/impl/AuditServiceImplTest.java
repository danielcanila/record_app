package com.daniel.record.service.impl;

import com.daniel.record.model.Audit;
import com.daniel.record.repository.AuditRepository;
import com.daniel.record.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

	@Mock
	private AuditRepository auditRepositoryMock;

	@Captor
	private ArgumentCaptor<Audit> captor;

	private AuditServiceImpl cut;

	@BeforeEach
	public void setup(){
		cut = new AuditServiceImpl(auditRepositoryMock);
	}

	@Test
	void storeAudit_success() {
		//given
		var requestIp = "request_ip";
		var requestUrl = "request_url";

		//when
		cut.storeAudit(requestIp,requestUrl);

		//then
		verify(auditRepositoryMock,times(1)).save(captor.capture());
		var auditObject = captor.getValue();
		assertEquals(requestIp,auditObject.getRequestIp());
		assertEquals(requestUrl,auditObject.getRequestUrl());
		assertNotNull(auditObject.getRequestTime());
	}

	@Test
	void storeAudit_exception_emptyRequestIp() {
		//given
		var requestIp = "";
		var requestUrl = "request_url";

		//when
		var thrown = Assertions.assertThrows(RuntimeException.class, () -> {
			cut.storeAudit(requestIp,requestUrl);
		});

		//then
		verify(auditRepositoryMock,times(0)).save(any());
		assertEquals(Constants.MANDATORY_IP_AND_URL, thrown.getMessage());
	}

	@Test
	void storeAudit_exception_emptyRequestUrl() {
		//given
		var requestIp = "request_ip";
		var requestUrl = "";

		//when
		var thrown = Assertions.assertThrows(RuntimeException.class, () -> {
			cut.storeAudit(requestIp,requestUrl);
		});

		//then
		verify(auditRepositoryMock,times(0)).save(any());
		assertEquals(Constants.MANDATORY_IP_AND_URL, thrown.getMessage());
	}
}