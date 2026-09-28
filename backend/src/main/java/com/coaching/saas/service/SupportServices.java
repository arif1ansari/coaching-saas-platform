package com.coaching.saas.service;

import com.coaching.saas.model.DocumentModels.User;
import com.coaching.saas.model.Enums;
import com.coaching.saas.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
class Bootstrap {
 Bootstrap(UserRepository users,PasswordEncoder encoder,@Value("${app.super-admin.email}")String email,@Value("${app.super-admin.password}")String password){
   if(users.findByEmail(email).isEmpty()) users.save(new User(null,email,encoder.encode(password),Enums.Role.SUPER_ADMIN,null,"Platform Owner",null,true,Instant.now(),Instant.now()));
 }
}
