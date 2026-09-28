package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.ClassSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate; import java.util.*;
public interface SessionRepository extends MongoRepository<ClassSession,String> {
 List<ClassSession> findByCoachingIdAndDate(String coachingId,LocalDate date); List<ClassSession> findByCoachingId(String coachingId); Optional<ClassSession> findBySessionIdAndCoachingId(String id,String coachingId);
}