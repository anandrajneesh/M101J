package homework.week3;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javadriver.BootstrapMongo;

import com.mongodb.BasicDBList;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class StudentHomework3 {

	public static void main(String[] args) {
		try {
			DBCollection students = BootstrapMongo.getCollectionFromMongoDB(
					"school", "students");
			DBCursor cursor = students.find();
			while (cursor.hasNext()) {
				DBObject student = cursor.next();
				BasicDBList scores = (BasicDBList) student.get("scores");
				Collections.sort(scores, new Comparator<Object>() {

					public int compare(Object o1, Object o2) {
						DBObject s1 = (DBObject) o1;
						DBObject s2 = (DBObject) o2;
						if (s1.get("type").equals(s2.get("type"))) {
							if ((Double) s1.get("score") > (Double) s2
									.get("score")) {
								return 1;
							}
							return -1;
						}
						return 1;
					}
				});
				System.out.println("/n/n "+ "-----------"+ "/n");
				System.out.println(student);
				scores.remove(0);
				System.out.println(student);
				System.out.println("/n/n "+ "-----------"+ "/n");
				students.save(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double min(BasicDBList list) {
		Collections.sort(list, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				DBObject s1 = (DBObject) o1;
				DBObject s2 = (DBObject) o2;
				if (s1.get("type").equals(s2.get("type"))) {
					if ((Double) s1.get("score") > (Double) s2.get("score")) {
						return 1;
					}
					return -1;
				}
				return 1;
			}
		});
		return 0;
	}
}
