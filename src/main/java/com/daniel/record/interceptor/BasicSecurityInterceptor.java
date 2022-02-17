package com.daniel.record.interceptor;

import com.daniel.record.exception.HttpUnauthorizedException;
import com.daniel.record.util.Constants;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BasicSecurityInterceptor implements HandlerInterceptor {

	@Value("${basic.security.token}")
	private String securityToken;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String authorization = request.getHeader("Authorization");
		if (!Objects.equals(securityToken, authorization)) {
			throw new HttpUnauthorizedException(Constants.INVALID_TOKEN_MESSAGE);
		}
		return true;
	}
}
