package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface ResultRepository extends MongoRepository<TestResult,String> { List<TestResult> findByTestIdAndCoachingId(String testId,String coachingId); }