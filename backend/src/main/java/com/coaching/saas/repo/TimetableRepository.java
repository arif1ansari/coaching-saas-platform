package com.coaching.saas.repo;
import com.coaching.saas.model.DocumentModels.Timetable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
public interface TimetableRepository extends MongoRepository<Timetable,String> { List<Timetable> findByCoachingId(String coachingId); }