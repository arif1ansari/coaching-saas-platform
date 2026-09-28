package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.CoachingCentre;
import com.coaching.saas.model.Enums;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
public interface CoachingCentreRepository extends MongoRepository<CoachingCentre,String> {
 Optional<CoachingCentre> findByCoachingId(String coachingId); boolean existsByCoachingId(String coachingId); long countByStatus(Enums.Status status);
}