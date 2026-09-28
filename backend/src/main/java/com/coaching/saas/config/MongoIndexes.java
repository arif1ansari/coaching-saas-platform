package com.coaching.saas.config;
import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.data.mongodb.core.MongoTemplate; import org.springframework.data.mongodb.core.index.Index;
@Configuration
public class MongoIndexes {
 @Bean CommandLineRunner indexes(MongoTemplate m){return args->{
   m.indexOps("users").ensureIndex(new Index().on("email",org.springframework.data.domain.Sort.Direction.ASC).unique());
   m.indexOps("coaching_centres").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).unique());
   m.indexOps("students").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("studentId",org.springframework.data.domain.Sort.Direction.ASC).unique());
   m.indexOps("subjects").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("code",org.springframework.data.domain.Sort.Direction.ASC).unique());
   m.indexOps("attendance").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("sessionId",org.springframework.data.domain.Sort.Direction.ASC).unique());
   m.indexOps("teachers").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("email",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("batches").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("name",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("fee_structures").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("studentId",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("tests").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("batchId",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("timetables").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("batchId",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("student_teacher_assignments").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("studentId",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("student_teacher_assignments").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("teacherId",org.springframework.data.domain.Sort.Direction.ASC));
   m.indexOps("student_teacher_assignments").ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC).on("studentId",org.springframework.data.domain.Sort.Direction.ASC).on("teacherId",org.springframework.data.domain.Sort.Direction.ASC).on("batchId",org.springframework.data.domain.Sort.Direction.ASC).on("subject",org.springframework.data.domain.Sort.Direction.ASC).unique());
   for(String c:new String[]{"class_sessions","fee_payments","test_results","audit_logs"})
     m.indexOps(c).ensureIndex(new Index().on("coachingId",org.springframework.data.domain.Sort.Direction.ASC));
 }; }
}
