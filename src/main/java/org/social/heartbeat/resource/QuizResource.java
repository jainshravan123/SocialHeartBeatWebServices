package org.social.heartbeat.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.QuizAnswer;
import org.social.heartbeat.bean.QuizDataForScoreCalculation;
import org.social.heartbeat.bean.QuizQuestion;
import org.social.heartbeat.bean.QuizQuestionAnswer;
import org.social.heartbeat.bean.QuizScore;
import org.social.heartbeat.bean.QuizSelectedAnswer;
import org.social.heartbeat.service.QuizService;

@Path("/quiz")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuizResource 
{
	
	QuizService quizService = new QuizService();
	
	@GET
	@Path("/all_questions")
	public ArrayList<QuizQuestion> getAllQuizQuestion() throws ClassNotFoundException, SQLException
	{
			return quizService.getAllQuizQuestion();
	}
	
	@GET
	@Path("/limited_questions/{numOfQus}")
	public ArrayList<QuizQuestion> getTenRandomQuestions(@PathParam("numOfQus") int numOfQus) throws ClassNotFoundException, SQLException
	{
		return quizService.getTenRandomQuestions(numOfQus);
	}
	
	
	@GET
	@Path("/all_answers")
	public ArrayList<QuizAnswer> getAllQuizAnswers() throws ClassNotFoundException, SQLException
	{
		return quizService.getAllQuizAnswers();
	}
	
	
	@GET
	@Path("/answer/{qusId}")
	public ArrayList<QuizAnswer> getAnswersForQuestion(@PathParam("qusId") int qusId) throws ClassNotFoundException, SQLException
	{
		return quizService.getAnswersForQuestion(qusId);
	}
	
	@GET
	@Path("/questionsWithAnswers")
	public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswers() throws ClassNotFoundException, SQLException
	{
		return quizService.getQuestionsWithAnswers();
	}
	
	@GET
	@Path("/questionsWithAnswers/{qusId}")
	public QuizQuestionAnswer getSingleQuestionWithAnswers(@PathParam("qusId") int qusId) throws ClassNotFoundException, SQLException
	{
		return quizService.getSingleQuestionWithAnswers(qusId);
	}
	
	@GET
	@Path("/questionsWithAnswersWithoutRightAns")
	public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswersWithoutRightAns() throws ClassNotFoundException, SQLException
	{
		return quizService.getQuestionsWithAnswersWithoutRightAns();
	}
	
 	@GET
	@Path("/limitedQuestionsWithAnswersWithoutRightAns/{numOfQus}")
	public ArrayList<QuizQuestionAnswer> getLimitedQuestionsWithAnswersWithoutRightAns(@PathParam("numOfQus") int numOfQus) throws ClassNotFoundException, SQLException
	{
		return quizService.getLimitedQuestionsWithAnswersWithoutRightAns(numOfQus);
	}
	
	@GET
	@Path("/questionsWithAnswersWithoutRightAns/{qusId}")
	public QuizQuestionAnswer getSingleQuestionWithAnswersWithoutRightAns(@PathParam("qusId") int qusId) throws ClassNotFoundException, SQLException
	{
		return quizService.getSingleQuestionWithAnswersWithoutRightAns(qusId);
	}
	
	
	@POST
	@Path("/calculateScore")
	public QuizScore calculateScore(QuizDataForScoreCalculation quizDataForScoreCalculation) throws ClassNotFoundException, SQLException
	{
		return quizService.calculateScore(quizDataForScoreCalculation);
	}
	
	@POST
	@Path("/postStartingQuizData")
	public QuizScore startingQuizInfo(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{
		return quizService.startingQuizInfo(quizScore);
	}
	
	@POST
	@Path("/postQuizScoreData/{tokenId}")
	public QuizScore submittingQuizScoreInfor(@PathParam("tokenId") int tokenId, QuizScore quizScore) throws ClassNotFoundException, SQLException, JSONException
	{
		return quizService.submittingQuizScoreInfor(quizScore, tokenId);
	}
	
	@POST
	@Path("/getQuizScore")
	public QuizScore getQuizScoreInformation(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{
		return quizService.getQuizScoreInformation(quizScore);
	}
}
