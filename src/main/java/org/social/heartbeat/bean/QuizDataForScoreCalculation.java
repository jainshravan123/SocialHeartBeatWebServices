package org.social.heartbeat.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuizDataForScoreCalculation 
{

	private ArrayList<QuizSelectedAnswer> quizSelectedAnswers;

	public ArrayList<QuizSelectedAnswer> getQuizSelectedAnswers() {
		return quizSelectedAnswers;
	}

	public void setQuizSelectedAnswers(ArrayList<QuizSelectedAnswer> quizSelectedAnswers) {
		this.quizSelectedAnswers = quizSelectedAnswers;
	}

	
}
