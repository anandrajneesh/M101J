package homework.week2;

import javadriver.BootstrapMongo;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class Homework2 {

	public static void main(String[] args) {
		try {
			DBCollection studentsDbCollection = BootstrapMongo.getCollectionFromMongoDB("students", "grades");
			QueryBuilder findQuery = QueryBuilder.start("type").is("homework");
			QueryBuilder sortQuery = QueryBuilder.start("student_id").is(1).and("score").is(-1);
			DBCursor cursor = studentsDbCollection.find(findQuery.get()).sort(sortQuery.get());
			try{
				DBObject prevStudentLowestScore = cursor.next();
				while(cursor.hasNext()){
					DBObject currentStudent =  cursor.next();
					if(((Integer)currentStudent.get("student_id")).intValue() != ((Integer)prevStudentLowestScore.get("student_id")).intValue()){
						System.out.println("Removing: "+prevStudentLowestScore);
						studentsDbCollection.remove(prevStudentLowestScore);
					}
					prevStudentLowestScore = currentStudent;
				}
			}finally{
				cursor.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
