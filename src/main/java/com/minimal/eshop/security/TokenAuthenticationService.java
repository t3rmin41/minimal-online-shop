package com.minimal.eshop.security;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

  private static final long EXPIRATIONTIME = 86400_000; // 1 day
  private static final String SECRET = "ThisIsASecret";
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER_STRING = "Authorization";

  public void addAuthentication(HttpServletResponse res, String email, Collection<? extends GrantedAuthority> authorities) {
    Claims claims = Jwts.claims().setSubject(email);
    claims.put("roles", authorities.stream().map(s -> s.toString()).collect(Collectors.toList()));
    
    String JWT = Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
  }

  public String getAuthenticatedUsername(HttpServletRequest request)  throws UserNotFoundException {
    String token = request.getHeader(HEADER_STRING);
    String email = null;
    if (token != null) {
      // parse the token.
      email = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
    }
    if (null == email) {
      throw new UserNotFoundException("User or token not found");
    }
    return email;
  }

  @SuppressWarnings("unchecked")
  public List<GrantedAuthority> getAuthenticatedUserRoles(HttpServletRequest request) throws UserNotFoundException {
    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    String token = request.getHeader(HEADER_STRING);
    Jws<Claims> claims = Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
    List<String> rolesAsString = (List<String>) claims.getBody().get("roles");
    rolesAsString.stream().forEach(rS -> {
      roles.add(new SimpleGrantedAuthority(rS));
    });
    return roles;
  }
  
}
