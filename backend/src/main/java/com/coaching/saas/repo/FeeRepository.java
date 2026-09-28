package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.FeeStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface FeeRepository extends MongoRepository<FeeStructure,String> {
 List<FeeStructure> findByCoachingId(String coachingId); Optional<FeeStructure> findByFeeIdAndCoachingId(String id,String coachingId);
}