package com.rest.example.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class RestAuthenticationFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(RestAuthenticationFilter.class);
	
	public static final String AUTHENTICATION_HEADER = "Authorization";
	private static final String USERNAME = "admin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

			boolean authenticationStatus = authenticate(authCredentials);

			if (authenticationStatus) {
				chain.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}

	}

	@Override
	public void destroy() {
	}
	
	private boolean authenticate(String authCredentials) {

		if (null == authCredentials) {
			return false;
		}
		
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = getUsernameAndPasswordAsDecoded (encodedUserPassword);
		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		
		LOGGER.debug("username = " + username + ".");
		LOGGER.debug("password = " + password + ".");

		// call some UserService/LDAP here
		return USERNAME.equals(username) && USERNAME.equals(password);
	}
	
	private String getUsernameAndPasswordAsDecoded(String authString) {
		try {
			byte[] decodedBytes = Base64.decodeBase64(authString);
			return new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}

}
