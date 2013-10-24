package javadriver;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class BootstrapMongo {

	public static DBCollection getCollectionFromMongoDB(String dbname, String collectionName) throws UnknownHostException{
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		DB db =  client.getDB(dbname);
		return db.getCollection(collectionName);
	}
}
