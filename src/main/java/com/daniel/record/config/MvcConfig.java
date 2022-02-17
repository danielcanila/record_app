package com.daniel.record.config;

import com.daniel.record.interceptor.AuditInterceptor;
import com.daniel.record.interceptor.BasicSecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private final BasicSecurityInterceptor basicSecurityInterceptor;
	private final AuditInterceptor auditInterceptor;

	public MvcConfig(BasicSecurityInterceptor basicSecurityInterceptor, AuditInterceptor auditInterceptor) {
		this.basicSecurityInterceptor = basicSecurityInterceptor;
		this.auditInterceptor = auditInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(basicSecurityInterceptor).addPathPatterns("/v1/record/*");
		registry.addInterceptor(auditInterceptor).addPathPatterns("/v1/record/*");
	}
}
