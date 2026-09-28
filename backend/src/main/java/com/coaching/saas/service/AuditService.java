package com.coaching.saas.service;

import com.coaching.saas.model.DocumentModels.AuditLog;
import com.coaching.saas.security.TenantContext;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class AuditService {
 private final org.springframework.data.mongodb.core.MongoTemplate mongo;
 private final IdService ids;
 public AuditService(org.springframework.data.mongodb.core.MongoTemplate mongo,IdService ids){this.mongo=mongo;this.ids=ids;}
 public void record(String action,String entityType,String entityId){var user=TenantContext.user();mongo.save(new AuditLog(null,ids.next("AUDIT"),user.coachingId(),user.id(),action,entityType,entityId,Instant.now()));}
}