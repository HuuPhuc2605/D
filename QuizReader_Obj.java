package execute_3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import model_3.Category;
import model_3.Questions;
import model_3.Quiz;

//"quiz_id": "quiz-001", 
//"name": "Java Basics Quiz", 
//"score": 100, 
////"questions": [ 
//"question_id": "Q002", 
//"text": "Which keyword is used to define a constant variable in Java?", 
//"options": ["const", "static", "final", "var"], 
//"correct_answer": "final" 
//} 
//], 
//"category": {  
//"category_id": "C001",  
//"name": "Java Programming"
public class QuizReader_Obj {
	public static Quiz parseQuiz(JsonObject jO) {
		String quiz = jO.getString("quiz_id");
		String name = jO.getString("name");
		int score = jO.getInt("score");
		// Array Questions
		List<Questions> questions = new ArrayList<Questions>();
		JsonArray qArray = jO.getJsonArray("questions");

		for (JsonValue vl : qArray) {
			JsonObject qJsonObject = vl.asJsonObject();

			// Array Options
			JsonArray oArray = qJsonObject.getJsonArray("options");
			List<String> oList = new ArrayList<String>();
			for (JsonValue opt : oArray) {
				oList.add(opt.toString());
			}

			questions.add(new Questions(qJsonObject.getString("question_id"), qJsonObject.getString("text"), oList,
					qJsonObject.getString("correct_answer")));
		}

		// Mảng category
		JsonObject cJsonObject = jO.getJsonObject("category");
		Category category = new Category(cJsonObject.getString("category_id"), cJsonObject.getString("name"));
		return new Quiz(quiz, name, score, questions, category);

	}

	public static void main(String[] args) {
		try (InputStream is = new FileInputStream("json/quizzes.json"); JsonReader reader = Json.createReader(is)) {
			JsonArray root = reader.readArray();
			List<Quiz> quizs = new ArrayList<Quiz>();
			for (JsonValue v : root) {
				if (v.getValueType() == JsonValue.ValueType.OBJECT) {
					quizs.add(parseQuiz(v.asJsonObject()));
				}
			}
			for (Quiz q : quizs) {
				System.out.println(q); // toString() của Quiz
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
