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

//import org.springframework.security.jwt.*;
//import org.springframework.security.jwt.codec.Codecs;
//import org.springframework.security.jwt.crypto.cipher.CipherMetadata;
//import org.springframework.security.jwt.crypto.sign.RsaSigner;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

//		String username = authentication.getPrincipal().toString();
//		Date now = new Date();
//		Date exp = new Date(now.getTime() + CommonUtils.JWT_EXPIRATION);
//
//		String jws = Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(exp)
//				.signWith(SignatureAlgorithm.HS256, CommonUtils.JWT_SECRET).compact();
//
//		response.addCookie(new Cookie("token", jws));

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			setDefaultTargetUrl("/admin/home");
			super.onAuthenticationSuccess(request, response, authentication);
		}
		if (roles.contains("ROLE_VENDOR")) {
			setDefaultTargetUrl("/shop/home");
			super.onAuthenticationSuccess(request, response, authentication);
		}
		if (roles.contains("ROLE_USER")) {
			setDefaultTargetUrl("/");
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
