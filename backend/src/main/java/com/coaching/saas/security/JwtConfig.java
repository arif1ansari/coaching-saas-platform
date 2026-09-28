package com.coaching.saas.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JwtConfig {
 @Bean JwtService jwtService(@Value("${app.jwt.secret}")String s,@Value("${app.jwt.expiration-ms}")long e){return new JwtService(s,e);}
}
