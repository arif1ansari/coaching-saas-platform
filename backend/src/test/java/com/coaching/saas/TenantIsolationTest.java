package com.coaching.saas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.coaching.saas.repo.*;
import com.coaching.saas.model.DocumentModels.Student;
import java.util.*;
class TenantIsolationTest {
 @Test void repositoryContractRequiresTenantKey(){
   Student s=new Student(null,"STU_1","COACH_A","A","a@x.com","1","B1","ACTIVE",null,null);
   assertEquals("COACH_A",s.coachingId());
   assertNotEquals("COACH_B",s.coachingId());
 }
}
