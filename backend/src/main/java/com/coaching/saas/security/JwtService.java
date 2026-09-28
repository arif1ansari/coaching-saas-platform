package com.coaching.saas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtService {
 private final SecretKey key;
 private final long expiration;
 public JwtService(String secret,long expiration){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expiration=expiration;}
 public String create(String email,String role,String coachingId,String name){
   Date now=new Date(); var builder=Jwts.builder().subject(email).claim("role",role).claim("name",name);
   if(coachingId!=null&&!coachingId.isBlank())builder.claim("coachingId",coachingId);
   return builder.issuedAt(now).expiration(new Date(now.getTime()+expiration)).signWith(key).compact();
 }
}