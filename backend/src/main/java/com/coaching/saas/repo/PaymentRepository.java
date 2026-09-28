package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.FeePayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface PaymentRepository extends MongoRepository<FeePayment,String> {
 List<FeePayment> findByFeeIdAndCoachingId(String feeId,String coachingId); List<FeePayment> findByCoachingId(String coachingId);
}