package Execute_3;

import java.io.FileOutputStream;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

public class DemoWriter {
	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream("json/output.json");
				JsonGenerator gen = Json.createGenerator(fos)) {

			gen.writeStartArray() // [
					.writeStartObject() // {
					.write("quiz_id", "quiz-401").write("title", "Java Short Quiz")

					// metadata
					.writeStartObject("metadata").writeNull("score") // score = null
					.write("age", 35.5) // double
					.write("level", "Easy").writeEnd() // }

					// questions
					.writeStartArray("questions").writeStartObject().write("id", "Q401")
					.write("text", "Which keyword is used to define a constant in Java?").writeStartArray("options")
					.write("const").write("final").write("static").write("var").writeEnd()
					.writeStartArray("correct_answers").write("final").writeEnd().writeEnd()
					// Thêm các câu hỏi khác tương tự...
					.writeEnd()

					// category
					.writeStartObject("category").write("id", "C100").writeNull("name") // name = null
					.writeEnd()

					.writeEnd() // }
					.writeEnd(); // ]

			System.out.println("Ghi JSON xong!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//
//package Execute_3;
//
//import java.io.FileOutputStream;
//import jakarta.json.Json;
//import jakarta.json.JsonArray;
//import jakarta.json.JsonObject;
//import jakarta.json.JsonWriter;
//
//public class DemoWriterObjectModel {
//    public static void main(String[] args) {
//        // metadata
//        JsonObject metadata = Json.createObjectBuilder()
//                .addNull("score")
//                .add("age", 35.5)
//                .add("level", "Easy")
//                .build();
//
//        // question 1
//        JsonObject q1 = Json.createObjectBuilder()
//                .add("id", "Q401")
//                .add("text", "Which keyword is used to define a constant in Java?")
//                .add("options", Json.createArrayBuilder()
//                        .add("const").add("final").add("static").add("var"))
//                .add("correct_answers", Json.createArrayBuilder()
//                        .add("final"))
//                .build();
//
//        // question 2
//        JsonObject q2 = Json.createObjectBuilder()
//                .add("id", "Q402")
//                .add("text", "Which of these are valid loops in Java?")
//                .add("options", Json.createArrayBuilder()
//                        .add("for").add("while").add("loop").add("do-while"))
//                .add("correct_answers", Json.createArrayBuilder()
//                        .add("for").add("while").add("do-while"))
//                .build();
//
//        // question 3
//        JsonObject q3 = Json.createObjectBuilder()
//                .add("id", "Q403")
//                .add("text", "Java is platform-independent.")
//                .add("options", Json.createArrayBuilder()
//                        .add("True").add("False"))
//                .add("correct_answers", Json.createArrayBuilder()
//                        .add(true)) // boolean
//                .build();
//
//        // questions array
//        JsonArray questions = Json.createArrayBuilder()
//                .add(q1).add(q2).add(q3)
//                .build();
//
//        // category
//        JsonObject category = Json.createObjectBuilder()
//                .add("id", "C100")
//                .addNull("name")
//                .build();
//
//        // quiz object
//        JsonObject quiz = Json.createObjectBuilder()
//                .add("quiz_id", "quiz-401")
//                .add("title", "Java Short Quiz")
//                .add("metadata", metadata)
//                .add("questions", questions)
//                .add("category", category)
//                .build();
//
//        // array of quizzes
//        JsonArray quizArray = Json.createArrayBuilder()
//                .add(quiz)
//                .build();
//
//        // Ghi ra file
//        try (FileOutputStream fos = new FileOutputStream("json/output_objectmodel.json");
//             JsonWriter writer = Json.createWriter(fos)) {
//            writer.writeArray(quizArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Ghi JSON theo Object Model xong!");
//    }
//}
