package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoHelloWorld {
	public static void main(String[] args) {
		try {
			MongoClient client = new MongoClient(new ServerAddress("localhost",
					27017));
			DB database = client.getDB("course");
			DBCollection collection = database.getCollection("users");
			DBObject dbObj = collection.findOne();
			System.out.println(dbObj);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
