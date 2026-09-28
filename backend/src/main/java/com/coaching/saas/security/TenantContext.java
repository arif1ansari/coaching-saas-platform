package com.coaching.saas.security;
import com.coaching.saas.model.DocumentModels.User;
import org.springframework.security.core.context.SecurityContextHolder;
public final class TenantContext {
 private TenantContext(){}
 public static User user(){
   Object d=SecurityContextHolder.getContext().getAuthentication().getDetails();
   if(!(d instanceof User u)) throw new IllegalStateException("Authenticated user context missing");
   return u;
 }
 public static String coachingId(){return user().coachingId();}
 public static boolean isSuperAdmin(){return user().role()==com.coaching.saas.model.Enums.Role.SUPER_ADMIN;}
}
