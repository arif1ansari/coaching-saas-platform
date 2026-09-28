package com.coaching.saas.controller;
import com.coaching.saas.api.ApiModels.*;
import com.coaching.saas.model.DocumentModels.*;
import com.coaching.saas.model.Enums;
import com.coaching.saas.repo.*;
import com.coaching.saas.security.JwtService;
import com.coaching.saas.service.IdService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.*; import java.util.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final CoachingCentreRepository centres; private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt; private final IdService ids;
 public AuthController(CoachingCentreRepository c,UserRepository u,PasswordEncoder e,JwtService j,IdService i){centres=c;users=u;encoder=e;jwt=j;ids=i;}
 @PostMapping("/signup") public AuthResponse signup(@Valid @RequestBody SignupRequest r){
   if(users.existsByEmail(r.email())) throw new IllegalArgumentException("Email already registered");
   String cid=ids.next("COACH");
   Instant now=Instant.now();
  CoachingCentre centre=centres.save(new CoachingCentre(null,cid,r.coachingName(),r.ownerName(),r.email(),r.mobile(),r.city(),Enums.Status.TRIAL,LocalDate.now().plusDays(14),now,now));
  try{users.save(new User(null,r.email(),encoder.encode(r.password()),Enums.Role.COACHING_ADMIN,cid,r.ownerName(),r.mobile(),true,now,now));}catch(RuntimeException error){centres.delete(centre);throw error;}
  User user=users.findByEmail(r.email()).orElseThrow();
  return new AuthResponse(jwt.create(r.email(),Enums.Role.COACHING_ADMIN.name(),cid,r.ownerName()),user.id(),user.email(),Enums.Role.COACHING_ADMIN.name(),cid,r.ownerName(),null);
 }
 @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){
   User u=users.findByEmail(r.email()).orElseThrow(()->new BadCredentialsException("Invalid email or password"));
   if(!u.active()||!encoder.matches(r.password(),u.passwordHash())) throw new BadCredentialsException("Invalid email or password");
   return new AuthResponse(jwt.create(u.email(),u.role().name(),u.coachingId(),u.name()),u.id(),u.email(),u.role().name(),u.coachingId(),u.name(),null);
 }
 @PostMapping("/change-password") public void changePassword(@Valid @RequestBody ChangePasswordRequest r){User u=users.findByEmail(com.coaching.saas.security.TenantContext.user().email()).orElseThrow();if(!encoder.matches(r.currentPassword(),u.passwordHash()))throw new org.springframework.security.authentication.BadCredentialsException("Current password is invalid");users.save(new User(u.id(),u.email(),encoder.encode(r.newPassword()),u.role(),u.coachingId(),u.name(),u.mobile(),u.active(),u.createdAt(),Instant.now()));}
}
