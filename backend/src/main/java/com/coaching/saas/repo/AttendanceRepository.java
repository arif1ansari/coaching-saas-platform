package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface AttendanceRepository extends MongoRepository<Attendance,String> {
 Optional<Attendance> findBySessionIdAndCoachingId(String sessionId,String coachingId); List<Attendance> findByCoachingIdOrderByDateDesc(String coachingId);
}