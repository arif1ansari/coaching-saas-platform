package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface StudentRepository extends MongoRepository<Student,String> {
 List<Student> findByCoachingId(String coachingId); Optional<Student> findByStudentIdAndCoachingId(String studentId,String coachingId); long countByCoachingId(String coachingId);
}