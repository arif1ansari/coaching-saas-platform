package com.coaching.saas.security;

import com.coaching.saas.model.DocumentModels.User;
import com.coaching.saas.repo.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Configuration
@EnableMethodSecurity
public class Security {
 @Bean CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origin:http://localhost:4200}")String origin){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(origin));c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));c.setAllowCredentials(true);UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();source.registerCorsConfiguration("/**",c);return source;}
 @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean UserDetailsService userDetailsService(UserRepository r){return email->r.findByEmail(email)
  .map(u->org.springframework.security.core.userdetails.User.withUsername(u.email()).password(u.passwordHash())
     .authorities(new SimpleGrantedAuthority("ROLE_"+u.role().name())).build())
   .orElseThrow(()->new UsernameNotFoundException("User not found"));}

 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwt)throws Exception{
   return http.csrf(c->c.disable()).cors(c->{}).sessionManagement(s->s.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
     .authorizeHttpRequests(a->a.requestMatchers("/api/auth/**","/actuator/health").permitAll().anyRequest().authenticated())
     .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class).build();
 }
 @Bean JwtFilter jwtFilter(UserRepository users,@Value("${app.jwt.secret}")String secret){return new JwtFilter(users,secret);}
}

class JwtFilter extends OncePerRequestFilter {
 private final UserRepository users; private final SecretKey key;
 JwtFilter(UserRepository u,String secret){users=u;key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws java.io.IOException,ServletException{
   String h=req.getHeader("Authorization");
   if(h!=null&&h.startsWith("Bearer ")){try{
     String email=Jwts.parser().verifyWith(key).build().parseSignedClaims(h.substring(7)).getPayload().getSubject();
     users.findByEmail(email).ifPresent(u->{
       var auth=new UsernamePasswordAuthenticationToken(u.email(),null,List.of(new SimpleGrantedAuthority("ROLE_"+u.role().name())));
       auth.setDetails(u); org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
     });
   }catch(Exception ignored){}}
   chain.doFilter(req,res);
 }
}

