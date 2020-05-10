package com.spring.security.jwt.interceptor;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.security.jwt.model.JwtUSer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokanUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String CLAM_KEY_USERNAME = "sub";
	static final String CLAM_KEY_AUDIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private long expiration;

	public String getUserNameFromToken(String token) {
		String userName = null;
		try {
			final Claims claim = getClaimsFromToken(token);
			userName = claim.getSubject();

		} catch (Exception e) {
			userName = null;

		}

		return userName;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claim = null;
		try {
			claim = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();

		} catch (Exception e) {
			claim = null;
		}

		return claim;
	}

	public boolean valiDateToken(String token, UserDetails userDetails) {
		JwtUSer user = (JwtUSer) userDetails;
		final String userName = getUserNameFromToken(token);

		return (userName.equals(user.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				expiration = claims.getExpiration();
			} else {
				expiration = null;
			}

		} catch (Exception e) {
			expiration = null;
		}

		return expiration;
	}

	public String genrateToken(JwtUSer userDetails) {
		System.out.println("==========genrateToken=============================");
		Map<String, Object> clamis = new HashMap<String, Object>();
		clamis.put(CLAM_KEY_USERNAME, userDetails.getUsername());
		clamis.put(CLAM_KEY_CREATED, new Date());
		return genrateToken(clamis);
	}

	private String genrateToken(Map<String, Object> clamis) {

		return Jwts.builder().setClaims(clamis).setExpiration(generatExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generatExpirationDate() {

		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

}
