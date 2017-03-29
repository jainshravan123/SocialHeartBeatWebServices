package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuizSelectedAnswer 
{

	private QuizQuestion quizQuestion;
	private QuizAnswer   quizAnswer;
	
	
	public QuizQuestion getQuizQuestion() {
		return quizQuestion;
	}
	public void setQuizQuestion(QuizQuestion quizQuestion) {
		this.quizQuestion = quizQuestion;
	}
	public QuizAnswer getQuizAnswer() {
		return quizAnswer;
	}
	public void setQuizAnswer(QuizAnswer quizAnswer) {
		this.quizAnswer = quizAnswer;
	}
}
