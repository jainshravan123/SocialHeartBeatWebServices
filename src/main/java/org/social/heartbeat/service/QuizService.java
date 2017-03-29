package org.social.heartbeat.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.QuizAnswer;
import org.social.heartbeat.bean.QuizDataForScoreCalculation;
import org.social.heartbeat.bean.QuizQuestion;
import org.social.heartbeat.bean.QuizQuestionAnswer;
import org.social.heartbeat.bean.QuizScore;
import org.social.heartbeat.bean.QuizSelectedAnswer;
import org.social.heartbeat.dao.QuizDAO;

public class QuizService 
{
	
	QuizDAO quizDAO = new QuizDAO();
	

	//Getting all of the Quiz Questions
	public ArrayList<QuizQuestion> getAllQuizQuestion() throws ClassNotFoundException, SQLException
	{
		return quizDAO.getAllQuizQuestion();
	}
	
	
	//Getting 10 random Quiz Questions
	public ArrayList<QuizQuestion> getTenRandomQuestions(int numOfQus) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getTenRandomQuestions(numOfQus);
	}
	
	//Getting all of the Quiz Answers
	public ArrayList<QuizAnswer> getAllQuizAnswers() throws ClassNotFoundException, SQLException
	{
		return quizDAO.getAllQuizAnswers();
	}
	
	//Getting Answers For A Particular Question
	public ArrayList<QuizAnswer> getAnswersForQuestion(int qusId) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getAnswersForQuestion(qusId);
	}
	
	//Getting All Questions With Answers
	public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswers() throws ClassNotFoundException, SQLException
	{
		return quizDAO.getQuestionsWithAnswers();
	}
	
	//Getting Single Question With Answers
	public QuizQuestionAnswer getSingleQuestionWithAnswers(int qusId) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getSingleQuestionWithAnswers(qusId);
	}
	
	//Getting All Question With Answers But Without Right Answer
	public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswersWithoutRightAns() throws ClassNotFoundException, SQLException
	{
		return quizDAO.getQuestionsWithAnswersWithoutRightAns();
	}
	
	//Getting Limited Questions With Answers But Without Right Answer
	public ArrayList<QuizQuestionAnswer> getLimitedQuestionsWithAnswersWithoutRightAns(int numOfQus) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getLimitedQuestionsWithAnswersWithoutRightAns(numOfQus);
	}
	
	//Getting Single Question With Answers But Without Right Answer
	public QuizQuestionAnswer getSingleQuestionWithAnswersWithoutRightAns(int qusId) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getSingleQuestionWithAnswersWithoutRightAns(qusId);
	}
	
	
	//Calculating Score on Server Side
	public QuizScore calculateScore(QuizDataForScoreCalculation quizDataForScoreCalculation) throws ClassNotFoundException, SQLException
	{
		return quizDAO.calculateScore(quizDataForScoreCalculation);
	}
	
	//Posting Starting Quiz Data
	public QuizScore startingQuizInfo(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{
		return quizDAO.startingQuizInfo(quizScore);
	}
	
	//Submitting Quiz Score 
	public QuizScore submittingQuizScoreInfor(QuizScore quizScore, int tokenId) throws ClassNotFoundException, SQLException, JSONException
	{
		return quizDAO.submittingQuizScoreInfor(quizScore, tokenId);
	}
	

	//Getting Score Information of User
	public QuizScore getQuizScoreInformation(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{
		return quizDAO.getQuizScoreInformation(quizScore);
	}

}
