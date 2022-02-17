package com.daniel.record.service.impl;

import com.daniel.record.model.Audit;
import com.daniel.record.repository.AuditRepository;
import com.daniel.record.service.AuditService;
import java.time.ZonedDateTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AuditServiceImpl implements AuditService {

	private final AuditRepository auditRepository;

	public AuditServiceImpl(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	@Override
	public void storeAudit(String requestIp, String requestUrl) {
		if (StringUtils.isBlank(requestIp) || StringUtils.isBlank(requestUrl)) {
			throw new RuntimeException("Request ip & url are mandatory for auditing.");
		}
		var audit = new Audit();
		audit.setRequestUrl(requestUrl);
		audit.setRequestIp(requestIp);
		audit.setRequestTime(ZonedDateTime.now());
		auditRepository.save(audit);
	}
}
