package Execute_3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import model_3.Category;
import model_3.Metadata;
import model_3.Questions;
import model_3.Quiz;

public class DemoReader {
	public static void main(String[] args) {
		List<Quiz> list = new ArrayList<Quiz>();
		String key = null;
		boolean inQuestionsArray = false;
		boolean inOptionsArray = false;
		boolean inCorrect_answersArray = false;

		Metadata metadata = null;
		Questions questions = null;
		Category category = null;
		Quiz quiz = null;
		List<String> options = null;
		List<String> correct_answers = null;
		try (InputStream inputStream = new FileInputStream("json/demo.json");
				JsonParser jsonParser = Json.createParser(inputStream)) {

			while (jsonParser.hasNext()) {
				Event event = jsonParser.next();
				switch (event) {
				case START_ARRAY:
					if ("questions".equals(key)) {
						inQuestionsArray = true;
						if (quiz != null && quiz.getQuestions() == null) {
							quiz.setQuestions(new ArrayList<Questions>());
						}
					} else if ("options".equals(key)) {
						inOptionsArray = true;
						options = new ArrayList<String>();
					} else if ("correct_answers".equals(key)) {
						inCorrect_answersArray = true;
						correct_answers = new ArrayList<String>();
					}
					break;
				case END_ARRAY:
					if (inOptionsArray) {
						inOptionsArray = false;
						if (questions != null && options != null) {
							questions.setOptions(options);

							options = null;

						}
					} else if (inCorrect_answersArray) {
						inCorrect_answersArray = false;
						if (questions != null && correct_answers != null) {

							questions.setCorrect_answers(correct_answers);

							correct_answers = null;
						}
					} else if (inQuestionsArray) {
						inQuestionsArray = false;
					}
					break;
				case START_OBJECT:
					if (quiz == null)
						quiz = new Quiz();
					else if (inQuestionsArray)
						questions = new Questions();
					else if ("metadata".equals(key)) {
						metadata = new Metadata();
					} else if ("category".equals(key)) {
						category = new Category();
					}
					break;
				case END_OBJECT:
					if (questions != null) {
						if (options != null) {
							questions.setOptions(options);
							options = null;
							inOptionsArray = false;
						} else if (correct_answers != null) {
							questions.setCorrect_answers(correct_answers);
							correct_answers = null;
							inCorrect_answersArray = false;
						}

						quiz.getQuestions().add(questions);
						questions = null;
					} else if (category != null) {
						quiz.setCategory(category);
						category = null;
					} else if (metadata != null) {
						quiz.setMetadata(metadata);
						metadata = null;
					} else if (quiz != null) {
						list.add(quiz);
						quiz = null;
					}
					break;
				case KEY_NAME:
					key = jsonParser.getString();
					break;

				case VALUE_STRING:
					String text = jsonParser.getString();
					if (inOptionsArray && options != null) {
						options.add(text);
					} else if (inCorrect_answersArray && correct_answers != null) {
						correct_answers.add(text);
					} else if ("quiz_id".equals(key))
						quiz.setQuiz_id(text);
					else if ("title".equals(key))
						quiz.setTitle(text);
					else if ("level".equals(key)) {

						metadata.setLevel(text);

					} else if ("id".equals(key)) {
						if (questions != null) {
							questions.setId(text);
						} else if (category != null) {
							category.setId(text);
						}

					} else if ("text".equals(key))

						questions.setText(text);
					else if ("name".equals(key))
						category.setName(text);

					break;
				case VALUE_TRUE:
					if (inCorrect_answersArray && correct_answers != null) {
						correct_answers.add("true");
					}
					break;
				case VALUE_FALSE:
					if (inCorrect_answersArray && correct_answers != null) {
						correct_answers.add("false");
					}
					break;
				case VALUE_NUMBER:
					if ("score".equals(key)) {

						metadata.setScore(jsonParser.getInt());

					} else if ("age".equals(key)) {
						quiz.setAge(jsonParser.getBigDecimal().doubleValue());
					}
					break;
				case VALUE_NULL:
					if ("score".equals(key) && metadata != null) {
						metadata.setScore(0); // default số
					} else if ("name".equals(key) && category != null) {
						category.setName(""); // default chuỗi rỗng
					}
					break;
				default:
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Quiz q : list) {
			System.out.println("Mã Quiz: " + q.getQuiz_id());
			System.out.println("Tiêu đề: " + q.getTitle());
			System.out.println("Tuổi: " + q.getAge());
			System.out.println("Điểm : " + q.getMetadata().getScore());
			System.out.println("Mức độ : " + q.getMetadata().getLevel());
			if (q.getQuestions() != null) {
				for (Questions ques : q.getQuestions()) {
					System.out.println("  Mã câu hỏi: " + ques.getId());
					System.out.println("  Nội dung: " + ques.getText());
					System.out.println("  Lựa chọn: " + ques.getOptions());
					System.out.println("  Kết quả: " + ques.getCorrect_answers());
				}
			}
			System.out.println("Danh mục ");
			if (q.getCategory() != null) {
				System.out.println("Mã danh mục: " + q.getCategory().getId());
				System.out.println("Tên danh mục: " + q.getCategory().getName());

			}
		}
		System.out.println("________________________________");

	}
}
