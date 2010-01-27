/*
 * LearnSet.java, package: data.objects
 * Object class for the LearnSets (question/anwswer pairs)
 */

package data.objects;

public class LearnSet {

	private int id;
	private String question;
	private String answer;

	public LearnSet(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
