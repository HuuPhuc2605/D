package execute_2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import model.Category;
import model.Question;
import model.Quiz;

public class QuizReader {

	public static void main(String[] args) {
		List<Quiz> quizList = new ArrayList<Quiz>();
		String key = null;
		boolean inQuestionArray = false;
		boolean inOptionsArray = false;

		Question question = null;
		Category category = null;
		Quiz quiz = null;
		List<String> options = null;

		try (InputStream inputStream = new FileInputStream("json/quiz.json");
				JsonParser jsonParser = Json.createParser(inputStream)) {
			while (jsonParser.hasNext()) {
				Event event = jsonParser.next();
				switch (event) {
				case START_ARRAY:
					if ("questions".equals(key)) {
						inQuestionArray = true;
						if (quiz != null && quiz.getQuestions() == null) {
							quiz.setQuestions(new ArrayList<Question>());
						}
					} else if ("options".equals(key)) {
						inOptionsArray = true;
						options = new ArrayList<String>();
					}
					break;
				case END_ARRAY:
					if (inOptionsArray) {
						inOptionsArray = false;
						if (question != null && options != null) {
							question.setOptions(options);
							options = null;
						}
					} else if (inQuestionArray) {
						inQuestionArray = false;
					}
					break;
				case START_OBJECT:
					if (quiz == null) {
						quiz = new Quiz();
					} else if (inQuestionArray) {
						question = new Question();
					} else if ("category".equals(key)) {
						category = new Category();
					}
					break;
				case END_OBJECT:
					if (question != null) {
						if (options != null) {
							question.setOptions(options);
							options = null;
							inOptionsArray = false;
						}
						quiz.getQuestions().add(question);
						question = null;
					} else if (category != null) {
						quiz.setCategory(category);
						category = null;
					} else if (quiz != null) {
						quizList.add(quiz);
						quiz = null;
					}
					break;
				case KEY_NAME:
					key = jsonParser.getString();
					break;

				case VALUE_STRING:
					String text = jsonParser.getString();
					if ("quiz_id".equals(key))
						quiz.setQuizId(text);
					else if ("name".equals(key)) {
						if (quiz != null)
							quiz.setName(text);
						if (category != null)
							category.setName(text);
					} else if ("question_id".equals(key)) {
						question.setQuestionId(text);
					} else if ("text".equals(key)) {
						question.setText(text);
					} else if ("correct_answer".equals(key)) {
						question.setCorrectAnswer(text);
					} else if ("category_id".equals(key)) {
						category.setCategoryId(text);
					} else if (inOptionsArray && options != null) {
						options.add(text);
					}
					break;
				case VALUE_NUMBER:
					if ("score".equals(key))
						quiz.setScore(jsonParser.getInt());
					break;
				default:
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Quiz q : quizList) {
			System.out.println("Mã Quiz: " + q.getQuizId());
			System.out.println("Tên Quiz: " + q.getName());
			System.out.println("Điểm tối đa: " + q.getScore());
			System.out.println("Danh sách câu hỏi:");
			if (q.getQuestions() != null) {
				for (Question ques : q.getQuestions()) {
					System.out.println("  Mã câu hỏi: " + ques.getQuestionId());
					System.out.println("  Nội dung: " + ques.getText());
					System.out.println("  Lựa chọn: " + ques.getOptions());
					System.out.println("  Đáp án đúng: " + ques.getCorrectAnswer());
					System.out.println("  ----------------------");
				}
			}
			System.out.println("Danh mục:");
			if (q.getCategory() != null) {
				System.out.println("  ID: " + q.getCategory().getCategoryId());
				System.out.println("  Tên: " + q.getCategory().getName());
			}
			System.out.println("***************************************************");
		}
	}
}
