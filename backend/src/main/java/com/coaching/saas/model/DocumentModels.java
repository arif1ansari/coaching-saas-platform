package com.coaching.saas.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public final class DocumentModels {
  private DocumentModels(){}

    @Document(collection = "coaching_centres")
    public record CoachingCentre(@Id String id, String coachingId, String name, String ownerName,
      String email, String mobile, String city, Enums.Status status, LocalDate trialEndsAt,
      Instant createdAt, Instant updatedAt, String subscriptionPlan, Instant trialStartDate,
      Instant subscriptionStartDate, Instant subscriptionEndDate) {
      public CoachingCentre(String id,String coachingId,String name,String ownerName,String email,String mobile,String city,Enums.Status status,LocalDate trialEndsAt,Instant createdAt,Instant updatedAt){this(id,coachingId,name,ownerName,email,mobile,city,status,trialEndsAt,createdAt,updatedAt,Enums.Plan.FREE_TRIAL.name(),createdAt,null,null);}
    }

    @Document(collection = "users")
    public record User(@Id String id, String email, String passwordHash, Enums.Role role,
      String coachingId, String name, String mobile, boolean active, Instant createdAt, Instant updatedAt) {}

    @Document(collection = "students")
    public record Student(@Id String id, String studentId, String coachingId, String name, String email,
      String mobile, String batchId, String status, Instant createdAt, Instant updatedAt,
      String admissionNumber, String firstName, String lastName, String gender, LocalDate dateOfBirth,
      String address, String guardianName, String guardianMobile, LocalDate admissionDate) {
      public Student(String id,String studentId,String coachingId,String name,String email,String mobile,String batchId,String status,Instant createdAt,Instant updatedAt){this(id,studentId,coachingId,name,email,mobile,batchId,status,createdAt,updatedAt,studentId,name,"","",null,"","","",createdAt==null?null:createdAt.atZone(java.time.ZoneOffset.UTC).toLocalDate());}
    }

    @Document(collection = "teachers")
    public record Teacher(@Id String id, String teacherId, String coachingId, String name, String email,
      String mobile, List<String> subjects, List<String> batchIds, String status, Instant createdAt, Instant updatedAt) {}

    @Document(collection = "batches")
    public record Batch(@Id String id, String batchId, String coachingId, String name, String course,
      List<String> subjects, List<String> teacherIds, String status, Instant createdAt, Instant updatedAt,
      String description, String academicYear, List<String> subjectIds, List<String> studentIds) {
      public Batch(String id,String batchId,String coachingId,String name,String course,List<String> subjects,List<String> teacherIds,String status,Instant createdAt,Instant updatedAt){this(id,batchId,coachingId,name,course,subjects,teacherIds,status,createdAt,updatedAt,"", "", subjects==null?List.of():subjects,List.of());}
    }

    @Document(collection = "subjects")
    public record Subject(@Id String id, String subjectId, String coachingId, String name,
      String code, String status, Instant createdAt, Instant updatedAt) {}

    @Document(collection = "class_sessions")
    public record ClassSession(@Id String id, String sessionId, String coachingId, String batchId,
      String teacherId, String subject, LocalDate date, String startTime, String endTime, String status,
      Instant createdAt, Instant updatedAt, String subjectId) {
      public ClassSession(String id,String sessionId,String coachingId,String batchId,String teacherId,String subject,LocalDate date,String startTime,String endTime,String status,Instant createdAt,Instant updatedAt){this(id,sessionId,coachingId,batchId,teacherId,subject,date,startTime,endTime,status,createdAt,updatedAt,subject);}
    }

    @Document(collection = "attendance")
    public record Attendance(@Id String id, String attendanceId, String coachingId, String sessionId,
      String batchId, LocalDate date, String teacherId, List<Map<String,String>> students,
      Instant markedAt, Instant updatedAt) {
      public Attendance(String id,String attendanceId,String coachingId,String sessionId,String batchId,LocalDate date,String teacherId,List<Map<String,String>> students,Instant markedAt){this(id,attendanceId,coachingId,sessionId,batchId,date,teacherId,students,markedAt,markedAt);}
    }

    @Document(collection = "student_teacher_assignments")
    public record StudentTeacherAssignment(@Id String id, String assignmentId, String coachingId,
      String studentId, String teacherId, String batchId, String subject, String status,
      Instant createdAt, Instant updatedAt) {}

    @Document(collection = "fee_structures")
    public record FeeStructure(@Id String id, String feeId, String coachingId, String studentId,
      double totalAmount, List<Map<String,Object>> installments, String status, Instant createdAt, Instant updatedAt,
      String batchId, double installmentAmount, LocalDate dueDate) {
      public FeeStructure(String id,String feeId,String coachingId,String studentId,double totalAmount,List<Map<String,Object>> installments,String status,Instant createdAt,Instant updatedAt){this(id,feeId,coachingId,studentId,totalAmount,installments,status,createdAt,updatedAt,"",0,null);}
    }

    @Document(collection = "fee_payments")
    public record FeePayment(@Id String id, String paymentId, String coachingId, String feeId,
      double amount, LocalDate paymentDate, Enums.PaymentMode mode, String reference, Instant createdAt,
      String studentId, Enums.PaymentMode paymentMode, String transactionReference, String status) {
      public FeePayment(String id,String paymentId,String coachingId,String feeId,double amount,LocalDate paymentDate,Enums.PaymentMode mode,String reference,Instant createdAt){this(id,paymentId,coachingId,feeId,amount,paymentDate,mode,reference,createdAt,"",mode,reference,"RECORDED");}
    }

    @Document(collection = "tests")
    public record Test(@Id String id, String testId, String coachingId, String batchId, String teacherId,
      String title, String subject, LocalDate testDate, double totalMarks, Instant createdAt, Instant updatedAt,
      String subjectId, String name, double passingMarks, String status) {
      public Test(String id,String testId,String coachingId,String batchId,String teacherId,String title,String subject,LocalDate testDate,double totalMarks,Instant createdAt,Instant updatedAt){this(id,testId,coachingId,batchId,teacherId,title,subject,testDate,totalMarks,createdAt,updatedAt,subject,title,0,"ACTIVE");}
    }

    @Document(collection = "test_results")
    public record TestResult(@Id String id, String resultId, String coachingId, String testId,
      String studentId, double marks, double percentage, String grade, Instant createdAt,
      double marksObtained, String remarks) {
      public TestResult(String id,String resultId,String coachingId,String testId,String studentId,double marks,double percentage,String grade,Instant createdAt){this(id,resultId,coachingId,testId,studentId,marks,percentage,grade,createdAt,marks,"");}
    }

    @Document(collection = "timetables")
    public record Timetable(@Id String id, String timetableId, String coachingId, String batchId,
      String teacherId, String subject, String dayOfWeek, String startTime, String endTime,
      String room, Instant createdAt, Instant updatedAt, String subjectId, String status) {
      public Timetable(String id,String timetableId,String coachingId,String batchId,String teacherId,String subject,String dayOfWeek,String startTime,String endTime,String room,Instant createdAt,Instant updatedAt){this(id,timetableId,coachingId,batchId,teacherId,subject,dayOfWeek,startTime,endTime,room,createdAt,updatedAt,subject,"ACTIVE");}
    }

    @Document(collection = "audit_logs")
    public record AuditLog(@Id String id, String auditId, String coachingId, String userId,
      String action, String entityType, String entityId, Instant timestamp) {}
}
