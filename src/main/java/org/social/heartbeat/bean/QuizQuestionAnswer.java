package org.social.heartbeat.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuizQuestionAnswer
{
	
	private QuizQuestion            quizQuestion;
	private ArrayList<QuizAnswer>   quizAnswer_list;
	
	public QuizQuestion getQuizQuestion()
	{
		return quizQuestion;
	}
	
	public void setQuizQuestion(QuizQuestion quizQuestion) 
	{
		this.quizQuestion = quizQuestion;
	}
	
	public ArrayList<QuizAnswer> getQuizAnswer_list()
	{
		return quizAnswer_list;
	}
	
	public void setQuizAnswer_list(ArrayList<QuizAnswer> quizAnswer_list) 
	{
		this.quizAnswer_list = quizAnswer_list;
	}
	
}
