package com.daniel.record.service;

public interface AuditService {

	/**
	 * Stores the audit given a clientIp and a requestUrl. Will throw exception if either of the provided parameters is
	 * blank.
	 * @param clientIp non blank string.
	 * @param requestUrl non blank string.
	 */
	void storeAudit(String clientIp, String requestUrl);
}
