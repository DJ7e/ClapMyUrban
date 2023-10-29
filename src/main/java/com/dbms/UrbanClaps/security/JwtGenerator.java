//package com.dbms.UrbanClaps.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtGenerator {
//
//    private final String jwtSecretKey = "DJBRAVO";
//
//    public String generateToken(Authentication authentication, String userType)  {
//        String username = authentication.getName();
//        Date currentDate = new Date();
//        int jwtexp = 5*60*60;
//        Date expiryDate = new Date(currentDate.getTime() + jwtexp);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512,jwtSecretKey )
//                .claim("usertype", userType)
//                .compact();
//        return token;
//    }
//
//    public String getUsernameFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecretKey)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public String getUserTypeFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecretKey)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.get("usertype").toString();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
//            return true;
//        } catch (Exception ex) {
//            throw new AuthenticationCredentialsNotFoundException("JWT token is not valid " + token);
//        }
//    }
//}
//
