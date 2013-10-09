package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkHelloWorld {
	public static void main(String[] args) {
		try {
			Spark.get(new Route("/") {
				
				@Override
				public Object handle(Request arg0, Response arg1) {
					return "Hello World Spark Style !!";
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
