package com.javaweb.shopping.utils;

import com.javaweb.shopping.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("jwt");

    private String jwtSecret = resourceBundle.getString("jwtSecret");

    private String jwtExpirationMs = resourceBundle.getString("jwtExpirationMs");

    public String generateJwtToken(Authentication authentication, HttpServletRequest request) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();
        Long time = Long.parseLong(jwtExpirationMs);
        Date expiryDate = new Date(now.getTime() + time);

        final Map<String, Object> claims = new HashMap<>();
        //add ip client adress
        claims.put("ip", getClientIp(request));
        claims.put("ua", getUserAgent(request));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public String getUserAgent(HttpServletRequest request) {
        String ua = "";
        if (request != null) {
            ua = request.getHeader("User-Agent");
        }
        return ua;
    }

    public boolean validateJwtToken(String jwt, HttpServletRequest request) {
        try {
            final String ipAddress = getClientIp(request);
            final String userAgent = getUserAgent(request);
            // not match return error
            if (!ipAddress.equals(getClientIpFromJwtToken(jwt))
                    || !userAgent.equals(getUserAgentFromJwtToken(jwt))) {
                return false;
            }
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getClientIpFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().get("ip").toString();
    }

    public String getUserAgentFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().get("ua").toString();
    }

    public String getUsernameFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }
}
