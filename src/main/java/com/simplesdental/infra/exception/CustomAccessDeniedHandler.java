package com.simplesdental.infra.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");

		Map<String, Object> errorAttributes = new HashMap<>();
		errorAttributes.put("status", HttpServletResponse.SC_FORBIDDEN);
		errorAttributes.put("error", "Forbidden");
		errorAttributes.put("message", accessDeniedException.getMessage());
		errorAttributes.put("path", request.getRequestURI());

		ObjectMapper mapper = new ObjectMapper();
		String responseBody = mapper.writeValueAsString(errorAttributes);
		response.getWriter().write(responseBody);
	}
}
