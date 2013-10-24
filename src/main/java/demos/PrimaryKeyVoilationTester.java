package demos;

import javadriver.BootstrapMongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class PrimaryKeyVoilationTester {
public static void main(String[] args) {
	try {
		DBCollection course = BootstrapMongo.getCollectionFromMongoDB("course", "demo");
		DBObject doc = new BasicDBObject("_id", "anand").append("profession", "programmer");
		course.insert(doc);
		course.insert(doc);
		
		
		
	}catch (MongoException e) {
			System.out.println(e.getClass().toString()+ e.getCode());
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
