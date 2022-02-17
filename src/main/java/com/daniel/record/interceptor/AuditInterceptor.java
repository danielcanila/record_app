package com.daniel.record.interceptor;

import com.daniel.record.service.AuditService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuditInterceptor implements HandlerInterceptor {

	private final AuditService auditService;

	public AuditInterceptor(AuditService auditService) {
		this.auditService = auditService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		var clientIp = request.getRemoteAddr();
		var requestURI = request.getRequestURI();
		auditService.storeAudit(clientIp, requestURI);
		return true;
	}

}
