package vn.com.unit.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import vn.com.unit.entity.Category;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.CategoryService;

@Service
public class CommonUtils {

//	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//	public static String JWT_SECRET = Encoders.BASE64.encode(key.getEncoded());
//	public static final String JWT_SECRET = "asdasdasdas`1``122113vs`2`1`12`12dfhjejrkqwje@8737316e893er9fe6y362738edc0-1-_--e-99p;l,.';[o[\"/kl`";

	private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public static final String JWT_SECRET = Encoders.BASE64.encode(key.getEncoded());

	public static final Long JWT_EXPIRATION = 600000L; // milliseconds

	public static int ROW_OF_PAGE = 3;

	public static String DEFAULT_PASSWORD = "123";

	public static String encodePassword(String rawPassword) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}
	
	@Autowired
	public static AccountService ACCOUNT_SERVICE;

	// Convert from ISO-8859-1 to UTF-8
	public static String convertEncode(String s) throws UnsupportedEncodingException {
		return new String(s.getBytes("ISO-8859-1"), "UTF-8");
	}

	public static Map<String, Object> getMapHeaderAtribute(Model model, CategoryService categoryService) {
		List<Category> categories = categoryService.findAllCategory();
//		model.addAttribute("categories", categories);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categories", categories);
		return map;
	}

	public static String getUsernameFromJWT(String token) {
		if (validateToken(token)) {
			Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

			return claims.getSubject();
		}
		
		return null;
	}

	private static boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
//			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
//			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//			log.error("JWT claims string is empty.");
		}
		return false;
	}
	
}
