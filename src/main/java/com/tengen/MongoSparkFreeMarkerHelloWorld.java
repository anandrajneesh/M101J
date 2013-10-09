package com.tengen;

import java.io.IOException;
import java.io.StringWriter;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MongoSparkFreeMarkerHelloWorld {
	public static void main(String[] args) {
		try {

			// Mongo Section
			MongoClient dbClient = new MongoClient(new ServerAddress(
					"localhost", 27017));
			DB database = dbClient.getDB("course");
			final DBCollection users = database.getCollection("users");

			// FreeMarker Section
			Configuration config = new Configuration();
			config.setClassForTemplateLoading(
					MongoSparkFreeMarkerHelloWorld.class, "/");
			final Template htmlTemplate = config.getTemplate("hello.ftl");
			final StringWriter outputWriter = new StringWriter();

			// Spark Section
			Spark.get(new Route("/") {

				@Override
				public Object handle(Request arg0, Response arg1) {
					DBObject user = users.findOne();
					try {
						htmlTemplate.process(user, outputWriter);
					} catch (Exception e) {
						halt(500);
						e.printStackTrace();
					}
					return outputWriter;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
