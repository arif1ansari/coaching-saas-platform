package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface TestRepository extends MongoRepository<Test,String> {
 List<Test> findByCoachingId(String coachingId); Optional<Test> findByTestIdAndCoachingId(String id,String coachingId);
}