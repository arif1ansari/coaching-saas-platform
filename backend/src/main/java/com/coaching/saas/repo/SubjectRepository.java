package com.coaching.saas.repo;

import com.coaching.saas.model.DocumentModels.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends MongoRepository<Subject,String> {
 List<Subject> findByCoachingId(String coachingId);
 Optional<Subject> findBySubjectIdAndCoachingId(String subjectId,String coachingId);
 boolean existsByCoachingIdAndCodeIgnoreCase(String coachingId,String code);
}