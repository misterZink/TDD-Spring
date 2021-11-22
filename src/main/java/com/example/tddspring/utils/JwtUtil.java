package com.example.tddspring.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

import static java.lang.System.currentTimeMillis;


public class JwtUtil {

    private static final String SECRET_KEY = "something_secret";


    public static String createUserToken(String username) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        final long ONE_HOUR_IN_MILLISECONDS = 36000000;
        Date tokenExpire = new Date(currentTimeMillis() + ONE_HOUR_IN_MILLISECONDS);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setSubject("User Token")
                .setExpiration(tokenExpire)
                .claim("user", username)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static boolean verifyUserToken(String jwtToken, String username) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwtToken)
                .getBody()
                .get("user")
                .equals(username);
    }
}
