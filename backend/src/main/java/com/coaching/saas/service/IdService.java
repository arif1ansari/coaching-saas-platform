package com.coaching.saas.service;

import org.bson.Document;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class IdService {
 private final MongoTemplate mongo;
 public IdService(MongoTemplate mongo){this.mongo=mongo;}
 public synchronized String next(String prefix){
	 Query counterQuery=Query.query(Criteria.where("_id").is(prefix));
	 if(mongo.findOne(counterQuery,Document.class,"id_counters")==null)mongo.getCollection("id_counters").insertOne(new Document("_id",prefix).append("value",highestExisting(prefix)));
	 Document counter=mongo.findAndModify(Query.query(Criteria.where("_id").is(prefix)),new Update().inc("value",1),FindAndModifyOptions.options().upsert(true).returnNew(true),Document.class,"id_counters");
	 Number value=counter==null?1:(Number)counter.get("value");
	 return prefix+"_"+String.format("%06d",value.longValue());
 }
 private long highestExisting(String prefix){
	 String collection=switch(prefix){case "COACH"->"coaching_centres";case "STU"->"students";case "TEA"->"teachers";case "BATCH"->"batches";case "SESSION"->"class_sessions";case "ATT"->"attendance";case "FEE"->"fee_structures";case "PAY"->"fee_payments";case "TEST"->"tests";case "RES"->"test_results";case "TT"->"timetables";case "SUB"->"subjects";default->null;};
	 if(collection==null)return 0;
	 String field=switch(prefix){case "COACH"->"coachingId";case "STU"->"studentId";case "TEA"->"teacherId";case "BATCH"->"batchId";case "SESSION"->"sessionId";case "ATT"->"attendanceId";case "FEE"->"feeId";case "PAY"->"paymentId";case "TEST"->"testId";case "RES"->"resultId";case "TT"->"timetableId";case "SUB"->"subjectId";default->null;};
	 if(field==null)return 0;
	 Document doc=mongo.getCollection(collection).find().sort(new Document(field,-1)).limit(1).first();
	 if(doc==null||doc.get(field)==null)return 0;
	 String id=String.valueOf(doc.get(field));int separator=id.lastIndexOf('_');
	 try{return Long.parseLong(separator<0?id:id.substring(separator+1));}catch(NumberFormatException ignored){return 0;}
 }
}