package com.coaching.saas.api;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.coaching.saas.model.Enums;

public final class ApiModels {
 private ApiModels(){}

 public record SignupRequest(@NotBlank @Size(max=120) String coachingName,@NotBlank String ownerName,
   @NotBlank @Email String email,@NotBlank @Size(min=10,max=20) String mobile,
   @NotBlank @Size(min=8,max=72) String password,@NotBlank String city) {}

 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password) {}
 public record ChangePasswordRequest(@NotBlank String currentPassword,@NotBlank @Size(min=8,max=72) String newPassword) {}
 public record CreateTeacher(@NotBlank String name,@NotBlank @Email String email,@NotBlank String mobile,
   List<String> subjects,List<String> batchIds) {}
 public record CreateBatch(@NotBlank String name,@NotBlank String course,List<String> subjects,List<String> teacherIds) {}
 public record CreateStudent(@NotBlank String name,@Email String email,@NotBlank String mobile,String batchId,List<Map<String,Object>> subjectAssignments) {}
 public record StudentSubjectAssignmentRequest(@NotBlank String subject,@NotBlank String teacherId,@NotBlank String batchId) {}
 public record CreateSubject(@NotBlank String name,@NotBlank String code) {}
 public record CreateSession(@NotBlank String batchId,@NotBlank String teacherId,@NotBlank String subject,
   @NotNull LocalDate date,@NotBlank String startTime,@NotBlank String endTime) {}
 public record AttendanceRequest(@NotEmpty List<Map<String,String>> students) {}
 public record CreateFee(@NotBlank String studentId,@Positive double totalAmount,
   @NotEmpty List<Map<String,Object>> installments) {}
 public record PaymentRequest(@Positive double amount,@NotNull LocalDate paymentDate,
   @NotNull Enums.PaymentMode mode,String reference) {}
 public record CreateTest(@NotBlank String title,@NotBlank String batchId,@NotBlank String subject,
   @NotNull LocalDate testDate,@Positive double totalMarks) {}
 public record ResultRequest(@NotEmpty List<Map<String,Object>> results) {}
 public record CreateTimetable(@NotBlank String batchId,@NotBlank String teacherId,@NotBlank String subject,
   @NotBlank String dayOfWeek,@NotBlank String startTime,@NotBlank String endTime,String room) {}
 public record AuthResponse(String token,String userId,String email,String role,String coachingId,String name,String temporaryPassword) {}
 public record ErrorResponse(String timestamp,int status,String code,String message,String path,Map<String,String> errors) {
   public ErrorResponse(String timestamp,String message,String path){this(timestamp,400,"REQUEST_ERROR",message,path,Map.of());}
 }
}
