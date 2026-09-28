package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface BatchRepository extends MongoRepository<Batch,String> {
 List<Batch> findByCoachingId(String coachingId); Optional<Batch> findByBatchIdAndCoachingId(String batchId,String coachingId); long countByCoachingId(String coachingId);
}