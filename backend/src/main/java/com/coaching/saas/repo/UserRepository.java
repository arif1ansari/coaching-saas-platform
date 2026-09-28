package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.User;
import com.coaching.saas.model.Enums;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface UserRepository extends MongoRepository<User,String> {
 Optional<User> findByEmail(String email); boolean existsByEmail(String email); List<User> findByCoachingId(String coachingId); long countByRole(Enums.Role role);
}