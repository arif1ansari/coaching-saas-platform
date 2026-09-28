package com.coaching.saas.controller;
import com.coaching.saas.model.DocumentModels.*; import com.coaching.saas.model.Enums; import com.coaching.saas.repo.*;
import com.coaching.saas.security.TenantContext; import org.springframework.web.bind.annotation.*; import org.springframework.http.HttpStatus; import org.springframework.web.server.ResponseStatusException; import java.time.Instant; import java.util.*;
@RestController @RequestMapping("/api/super-admin")
public class SuperAdminController {
 final CoachingCentreRepository centres; final UserRepository users; final StudentRepository students; final TeacherRepository teachers;
 SuperAdminController(CoachingCentreRepository c,UserRepository u,StudentRepository s,TeacherRepository t){centres=c;users=u;students=s;teachers=t;}
 void check(){if(!TenantContext.isSuperAdmin())throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Super admin access required");}
 @GetMapping("/dashboard") Map<String,Object> dashboard(){check();return Map.of("coachingCentres",centres.count(),"active",centres.countByStatus(Enums.Status.ACTIVE),"trial",centres.countByStatus(Enums.Status.TRIAL),"expired",centres.countByStatus(Enums.Status.EXPIRED),"students",students.count(),"teachers",teachers.count());}
 @GetMapping("/coaching-centres") List<CoachingCentre> list(@RequestParam(defaultValue="")String search,@RequestParam(defaultValue="")String status){check();String term=search.trim().toLowerCase();return centres.findAll().stream().filter(c->term.isEmpty()||c.name().toLowerCase().contains(term)||c.coachingId().toLowerCase().contains(term)||c.email().toLowerCase().contains(term)).filter(c->status.isBlank()||c.status().name().equalsIgnoreCase(status)).toList();}
 @GetMapping("/coaching-centres/{coachingId}") CoachingCentre detail(@PathVariable String coachingId){check();return centres.findByCoachingId(coachingId).orElseThrow();}
 @PatchMapping("/coaching-centres/{coachingId}/status") CoachingCentre status(@PathVariable String coachingId,@RequestBody Map<String,String> body){check();CoachingCentre c=centres.findByCoachingId(coachingId).orElseThrow();Enums.Status s=Enums.Status.valueOf(body.get("status"));return centres.save(new CoachingCentre(c.id(),c.coachingId(),c.name(),c.ownerName(),c.email(),c.mobile(),c.city(),s,c.trialEndsAt(),c.createdAt(),Instant.now(),c.subscriptionPlan(),c.trialStartDate(),c.subscriptionStartDate(),c.subscriptionEndDate()));}
}
