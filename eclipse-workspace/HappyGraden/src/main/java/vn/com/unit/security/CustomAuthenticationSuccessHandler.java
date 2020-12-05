package vn.com.unit.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import io.jsonwebtoken.Jwts; // jjwt
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys; // jjwt-api
import vn.com.unit.utils.CommonUtils;

import java.security.Key;


public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			setDefaultTargetUrl("/admin");
			super.onAuthenticationSuccess(request, response, authentication);
		}
		if (roles.contains("ROLE_USER")) {
			setDefaultTargetUrl("/");
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
