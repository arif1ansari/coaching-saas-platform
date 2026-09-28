package com.coaching.saas.service;
import com.coaching.saas.model.DocumentModels.CoachingCentre;
import com.coaching.saas.model.Enums;
import com.coaching.saas.repo.CoachingCentreRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.*;
@Component
public class TrialGuard {
 private final CoachingCentreRepository repo;
 public TrialGuard(CoachingCentreRepository r){repo=r;}
 public void check(String coachingId){
   if(coachingId==null)return;
   repo.findByCoachingId(coachingId).ifPresent(c->{if(c.status()==Enums.Status.EXPIRED||(c.trialEndsAt()!=null&&c.trialEndsAt().isBefore(LocalDate.now())&&c.status()==Enums.Status.TRIAL))throw new IllegalStateException("Trial/subscription expired. Contact administrator.");if(c.status()==Enums.Status.SUSPENDED)throw new IllegalStateException("Coaching account is suspended.");});
 }
 @Scheduled(cron="0 0 * * * *")
 public void expireTrials(){repo.findAll().stream().filter(c->c.status()==Enums.Status.TRIAL&&c.trialEndsAt()!=null&&c.trialEndsAt().isBefore(LocalDate.now())).forEach(c->repo.save(new CoachingCentre(c.id(),c.coachingId(),c.name(),c.ownerName(),c.email(),c.mobile(),c.city(),Enums.Status.EXPIRED,c.trialEndsAt(),c.createdAt(),Instant.now(),c.subscriptionPlan(),c.trialStartDate(),c.subscriptionStartDate(),c.subscriptionEndDate())));}
}