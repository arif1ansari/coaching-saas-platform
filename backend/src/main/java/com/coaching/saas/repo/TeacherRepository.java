package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface TeacherRepository extends MongoRepository<Teacher,String> {
 List<Teacher> findByCoachingId(String coachingId); Optional<Teacher> findByTeacherIdAndCoachingId(String teacherId,String coachingId); long countByCoachingId(String coachingId);
}