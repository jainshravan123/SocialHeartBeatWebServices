package org.social.heartbeat.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuizAnswer 
{

	private int id;
	private String answer;
	private QuizQuestion question;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public QuizQuestion getQuestion() {
		return question;
	}
	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}
	
}
