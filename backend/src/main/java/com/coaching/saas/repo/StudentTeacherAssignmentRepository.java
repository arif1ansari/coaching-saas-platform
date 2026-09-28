package com.coaching.saas.repo;

import com.coaching.saas.model.DocumentModels.StudentTeacherAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentTeacherAssignmentRepository extends MongoRepository<StudentTeacherAssignment, String> {
    List<StudentTeacherAssignment> findByCoachingId(String coachingId);
    List<StudentTeacherAssignment> findByCoachingIdAndStudentId(String coachingId, String studentId);
    List<StudentTeacherAssignment> findByCoachingIdAndTeacherId(String coachingId, String teacherId);
    List<StudentTeacherAssignment> findByCoachingIdAndBatchId(String coachingId, String batchId);
    Optional<StudentTeacherAssignment> findByCoachingIdAndStudentIdAndTeacherIdAndBatchIdAndSubject(String coachingId, String studentId, String teacherId, String batchId, String subject);
    boolean existsByCoachingIdAndStudentIdAndTeacherIdAndBatchIdAndSubject(String coachingId, String studentId, String teacherId, String batchId, String subject);
    List<StudentTeacherAssignment> findByCoachingIdAndStudentIdAndBatchId(String coachingId, String studentId, String batchId);
    List<StudentTeacherAssignment> findByCoachingIdAndTeacherIdAndSubject(String coachingId, String teacherId, String subject);
    void deleteByCoachingIdAndStudentId(String coachingId, String studentId);
}
