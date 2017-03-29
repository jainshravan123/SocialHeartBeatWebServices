package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.social.heartbeat.bean.QuizAnswer;
import org.social.heartbeat.bean.QuizDataForScoreCalculation;
import org.social.heartbeat.bean.QuizQuestion;
import org.social.heartbeat.bean.QuizQuestionAnswer;
import org.social.heartbeat.bean.QuizScore;
import org.social.heartbeat.bean.QuizSelectedAnswer;
import org.social.heartbeat.bean.UserFirebaseToken;
import org.social.heartbeat.client.QuizNotificationClient;
import org.social.heartbeat.util.DBConnection;

public class QuizDAO 
{

	
	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	    //Getting all of the Quiz Questions
		public ArrayList<QuizQuestion> getAllQuizQuestion() throws ClassNotFoundException, SQLException
		{
			
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_question");
			rs1  = ps1.executeQuery();
			
			ArrayList<QuizQuestion> quiz_questions_list = new ArrayList<QuizQuestion>();
			
			while(rs1.next())
			{
			
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));
				quizQuestion.setRight_ans(rs1.getInt(3));
				
				quiz_questions_list.add(quizQuestion);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return quiz_questions_list;
		}
		
		//Getting 10 random Quiz Questions
		public ArrayList<QuizQuestion> getTenRandomQuestions(int numOfQus) throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_question ORDER BY RAND() LIMIT ?");
			ps1.setInt(1, numOfQus);
			rs1  = ps1.executeQuery();
			
			ArrayList<QuizQuestion> quiz_questions_list = new ArrayList<QuizQuestion>();
			
			while(rs1.next())
			{
			
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));
				quizQuestion.setRight_ans(rs1.getInt(3));
				
				quiz_questions_list.add(quizQuestion);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return quiz_questions_list;
		}
		
		//Getting all of the Quiz Answers
		public ArrayList<QuizAnswer> getAllQuizAnswers() throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_answer");
			rs1  = ps1.executeQuery();
			
			ArrayList<QuizAnswer> quiz_answers_list = new ArrayList<QuizAnswer>();
			while(rs1.next())
			{
			
				QuizAnswer quizAnswer = new QuizAnswer();
				quizAnswer.setId(rs1.getInt(1));
				quizAnswer.setAnswer(rs1.getString(2));
				
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(3));
				quizAnswer.setQuestion(quizQuestion);
				
				
				quiz_answers_list.add(quizAnswer);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return quiz_answers_list;
		}
		
		//Getting Answers For A Particular Question
		public ArrayList<QuizAnswer> getAnswersForQuestion(int qusId) throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_answer where qus_id=?");
			ps1.setInt(1, qusId);
			rs1  = ps1.executeQuery();
			
			ArrayList<QuizAnswer> quiz_answers_list = new ArrayList<QuizAnswer>();
			while(rs1.next())
			{
			
				QuizAnswer quizAnswer = new QuizAnswer();
				quizAnswer.setId(rs1.getInt(1));
				quizAnswer.setAnswer(rs1.getString(2));
				
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(3));
				quizAnswer.setQuestion(quizQuestion);
				
				quiz_answers_list.add(quizAnswer);
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return quiz_answers_list;
		}
		
		
		//Getting All Questions With Answers
		public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswers() throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();
			//ps1  = con1.prepareStatement("select h1.id, h1.question, h2.id, h2.answer, h2.right_ans from hb_quiz_question h1, hb_quiz_answer h2 where h1.id = h2.qus_id;");
			ps1  = con1.prepareStatement("select * from hb_quiz_question");
			rs1  = ps1.executeQuery();
			
			ArrayList<QuizQuestionAnswer> quizQuestionAnswerList = new ArrayList<QuizQuestionAnswer>();
			
			while(rs1.next())
			{
			
				QuizQuestionAnswer quizQuestionAnswer = new QuizQuestionAnswer();
				
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));
				quizQuestion.setRight_ans(rs1.getInt(3));

				PreparedStatement ps2 = con1.prepareStatement("select * from hb_quiz_answer where qus_id = ?");
				ps2.setInt(1,  quizQuestion.getId());
				ResultSet rs2   = ps2.executeQuery();
				
				ArrayList<QuizAnswer> quiz_answer_list = new ArrayList<QuizAnswer>();
				
				while(rs2.next())
				{
					
					QuizAnswer quizAnswer = new QuizAnswer();
					quizAnswer.setId(rs2.getInt(1));
					quizAnswer.setAnswer(rs2.getString(2));
					quiz_answer_list.add(quizAnswer);
				}
				
				
				quizQuestionAnswer.setQuizQuestion(quizQuestion);
				quizQuestionAnswer.setQuizAnswer_list(quiz_answer_list);
		
				rs2.close();
				ps2.close();
			
				quizQuestionAnswerList.add(quizQuestionAnswer);
				
			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return quizQuestionAnswerList;
		}
		
		
		//Getting Single Question With Answers
		public QuizQuestionAnswer getSingleQuestionWithAnswers(int qusId) throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_question where id = ?");
			ps1.setInt(1, qusId);
			rs1  = ps1.executeQuery();
					
			QuizQuestionAnswer quizQuestionAnswer = new QuizQuestionAnswer();
					
			while(rs1.next())
			{
						
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));
				quizQuestion.setRight_ans(rs1.getInt(3));

				PreparedStatement ps2 = con1.prepareStatement("select * from hb_quiz_answer where qus_id = ?");
				ps2.setInt(1,  quizQuestion.getId());
				ResultSet rs2   = ps2.executeQuery();
						
				ArrayList<QuizAnswer> quiz_answer_list = new ArrayList<QuizAnswer>();
						
				while(rs2.next())
				{		
					QuizAnswer quizAnswer = new QuizAnswer();
					quizAnswer.setId(rs2.getInt(1));
					quizAnswer.setAnswer(rs2.getString(2));
					quiz_answer_list.add(quizAnswer);
			   }					
			   quizQuestionAnswer.setQuizQuestion(quizQuestion);
			   quizQuestionAnswer.setQuizAnswer_list(quiz_answer_list);
				
			   rs2.close();
			   ps2.close();
									
		    }
					
		   rs1.close();
		   ps1.close();
		   con1.close();
					
		return quizQuestionAnswer;
	}
		
		//Getting All Question With Answers But Without Right Answer
		public ArrayList<QuizQuestionAnswer> getQuestionsWithAnswersWithoutRightAns() throws ClassNotFoundException, SQLException
		{
			 con1 = db.getConnection();
			 ps1  = con1.prepareStatement("select * from hb_quiz_question");
			 rs1  = ps1.executeQuery();
					
			 ArrayList<QuizQuestionAnswer> quizQuestionAnswerList = new ArrayList<QuizQuestionAnswer>();
					
			 while(rs1.next())
			 {
					
			 	QuizQuestionAnswer quizQuestionAnswer = new QuizQuestionAnswer();
						
						QuizQuestion quizQuestion = new QuizQuestion();
						quizQuestion.setId(rs1.getInt(1));
						quizQuestion.setQuestion(rs1.getString(2));

						PreparedStatement ps2 = con1.prepareStatement("select * from hb_quiz_answer where qus_id = ?");
						ps2.setInt(1,  quizQuestion.getId());
						ResultSet rs2   = ps2.executeQuery();
						
						ArrayList<QuizAnswer> quiz_answer_list = new ArrayList<QuizAnswer>();
						
						while(rs2.next())
						{
							QuizAnswer quizAnswer = new QuizAnswer();
							quizAnswer.setId(rs2.getInt(1));
							quizAnswer.setAnswer(rs2.getString(2));
							quiz_answer_list.add(quizAnswer);
						}
						
						
						quizQuestionAnswer.setQuizQuestion(quizQuestion);
						quizQuestionAnswer.setQuizAnswer_list(quiz_answer_list);
				
						rs2.close();
						ps2.close();
					
						quizQuestionAnswerList.add(quizQuestionAnswer);
						
					}
					
					rs1.close();
					ps1.close();
					con1.close();
					
					return quizQuestionAnswerList;
	 }	
		
	
		//Getting Limited Amount Each Type of Question With Answers Without Right Answer 
	/*	public ArrayList<QuizQuestionAnswer> getLimitedEachCategoryQuestionWithAnsWithoutRightAns(int numOfQus) throws ClassNotFoundException, SQLException
		{
			
		}
		
	*/
		//Getting Limited Question With Answers But Without Right Answer
		public ArrayList<QuizQuestionAnswer> getLimitedQuestionsWithAnswersWithoutRightAns(int numOfQus) throws ClassNotFoundException, SQLException
		{
			con1 = db.getConnection();  
			//ps1  = con1.prepareStatement("select * from hb_quiz_question ORDER BY RAND() LIMIT ?");
			ps1 = con1.prepareStatement("Select * from ((select * from hb_quiz_question as hb1 where hb1.category_id = 1 order by RAND() limit 4) union (select * from hb_quiz_question as hb1 where hb1.category_id = 2 order by RAND() limit 4) union (select * from hb_quiz_question as hb1 where hb1.category_id = 3 order by RAND() limit 4) union (select * from hb_quiz_question as hb1 where hb1.category_id = 4 order by RAND() limit 4)) as abc order by rand() limit ?");
			ps1.setInt(1, numOfQus);
			rs1  = ps1.executeQuery();
							
			ArrayList<QuizQuestionAnswer> quizQuestionAnswerList = new ArrayList<QuizQuestionAnswer>();
							
			while(rs1.next())
			{
				QuizQuestionAnswer quizQuestionAnswer = new QuizQuestionAnswer();
								
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));
				//quizQuestion.setRight_ans(rs1.getInt(3)); 
				//no need to show right answer for the question here
				quizQuestion.setCategory_id(rs1.getInt(4));
				
				PreparedStatement ps2 = con1.prepareStatement("select * from hb_quiz_answer where qus_id = ?");
				ps2.setInt(1,  quizQuestion.getId());
				ResultSet rs2   = ps2.executeQuery();
								
				ArrayList<QuizAnswer> quiz_answer_list = new ArrayList<QuizAnswer>();
								
				while(rs2.next())
				{
				   QuizAnswer quizAnswer = new QuizAnswer();
				   quizAnswer.setId(rs2.getInt(1));
				   quizAnswer.setAnswer(rs2.getString(2));
				   quiz_answer_list.add(quizAnswer);
				}
				
				quizQuestionAnswer.setQuizQuestion(quizQuestion);
				quizQuestionAnswer.setQuizAnswer_list(quiz_answer_list);
						
				rs2.close();
				ps2.close();
							
				quizQuestionAnswerList.add(quizQuestionAnswer);
								
			}
							
		rs1.close();
		ps1.close();
		con1.close();
							
	   return quizQuestionAnswerList;
	}	
		
		
		
	//Getting Limited Question With Answers From Each Category But Without Right Answer	
		
		
	//Getting Single Question With Answers But Without Right Answer
	public QuizQuestionAnswer getSingleQuestionWithAnswersWithoutRightAns(int qusId) throws ClassNotFoundException, SQLException
	{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("select * from hb_quiz_question where id = ?");
			ps1.setInt(1, qusId);
			rs1  = ps1.executeQuery();
							
			QuizQuestionAnswer quizQuestionAnswer = new QuizQuestionAnswer();
							
			while(rs1.next())
			{			
				QuizQuestion quizQuestion = new QuizQuestion();
				quizQuestion.setId(rs1.getInt(1));
				quizQuestion.setQuestion(rs1.getString(2));

				PreparedStatement ps2 = con1.prepareStatement("select * from hb_quiz_answer where qus_id = ?");
				ps2.setInt(1,  quizQuestion.getId());
				ResultSet rs2   = ps2.executeQuery();
								
						ArrayList<QuizAnswer> quiz_answer_list = new ArrayList<QuizAnswer>();
								
						while(rs2.next())
						{		
							QuizAnswer quizAnswer = new QuizAnswer();
							quizAnswer.setId(rs2.getInt(1));
							quizAnswer.setAnswer(rs2.getString(2));
							quiz_answer_list.add(quizAnswer);
					   }					
					   quizQuestionAnswer.setQuizQuestion(quizQuestion);
					   quizQuestionAnswer.setQuizAnswer_list(quiz_answer_list);
						
					   rs2.close();
					   ps2.close();
											
				    }
							
				   rs1.close();
				   ps1.close();
				   con1.close();
							
				return quizQuestionAnswer;
	 }
	
	
		
		
	//Calculating Score on Server Side
	public QuizScore calculateScore(QuizDataForScoreCalculation quizDataForScoreCalculation) throws ClassNotFoundException, SQLException
	{
		int score = 0;
		con1 = db.getConnection();
		
		ArrayList<QuizSelectedAnswer> quizSelectedAnsList = new ArrayList<QuizSelectedAnswer>();
		
		quizSelectedAnsList = 	quizDataForScoreCalculation.getQuizSelectedAnswers();
		
		ArrayList<Integer> interest_list = new ArrayList<Integer>();
		
		for(QuizSelectedAnswer quizSelectedAnswer : quizSelectedAnsList)
		{
			
			ps1  = con1.prepareStatement("select right_ans from hb_quiz_question where id = ?");
			ps1.setInt(1, quizSelectedAnswer.getQuizQuestion().getId());
		
			rs1  = ps1.executeQuery();
			
			while(rs1.next())
			{
				int right_ans_id = rs1.getInt(1);
				if(right_ans_id == quizSelectedAnswer.getQuizAnswer().getId())
				{
					QuizQuestion quizQuestion = interestCategory(quizSelectedAnswer.getQuizQuestion());
					interest_list.add(quizQuestion.getCategory_id());
					score++;
					break;
				}
			}
			
			rs1.close();
			ps1.close();
			
		}
		
		con1.close();
		
	
		QuizScore quizScore = new QuizScore();
		quizScore.setScore(score);
		//quizScore.setArea_of_interest_cat_id(getMaxFrequentNumber(interest_list));
		quizScore.setArea_of_interest_cat_id(getMaxFrequentNumber(interest_list));
		
		return quizScore;
	}
	
	
	
	//Posting Starting Quiz Data
	public QuizScore startingQuizInfo(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{
		Random rng = new Random();
		int rv1 = rng.nextInt(quizScore.getUser().getId());
		
		Date date = new Date();
		long time  = date.getTime();
		
		String rv2 = String.valueOf(rv1) + String.valueOf(time);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String start_time  = df.format(dateobj);
		
		con1 =  db.getConnection();
		String generatedColumns[] = {"id"};
		ps1  = con1.prepareStatement("insert into hb_quiz_score_log(quiz_id, user_id, employee_id, start_time, status, score, completion_time, area_of_interest_cat_id) values(?, ?, ?, ?, ?, ?, ?, ?)", generatedColumns);
		ps1.setString(1, rv2);
		ps1.setString(2, String.valueOf(quizScore.getUser().getId()));
		ps1.setString(3, quizScore.getUser().getEmp_id());
		ps1.setString(4, start_time);
		ps1.setInt(5, 0);
		ps1.setInt(6, 0);
		ps1.setString(7, "00:00:00");
		ps1.setInt(8, 0);
		
		 int result  = ps1.executeUpdate();
		    
		 rs1 = ps1.getGeneratedKeys();
		
		 
		 if(result > 0)
		 {
			 while(rs1.next())
			    {
				    quizScore.setId(rs1.getInt(1));
			    }
		 }
		 
		 quizScore.setQuiz_id(rv2); 
		 quizScore.setStart_time(start_time);	
		 quizScore.setNo_of_qus(Integer.parseInt(getColumnDefaultValue(con1, "hb_quiz_score","no_of_qus")));
		 
		 rs1.close();
		 ps1.close();
		 con1.close();
		
		return quizScore;
	}
	
	
	//Submitting Quiz Score 
	public QuizScore submittingQuizScoreInfor(QuizScore quizScore, int tokenId) throws ClassNotFoundException, SQLException, JSONException
	{
	
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String completion_time = df.format(dateobj);
		
		con1 =  db.getConnection();
		ps1  = con1.prepareStatement("update hb_quiz_score_log set score = ?, completion_time = ?, status = ?, area_of_interest_cat_id = ? where quiz_id = ? && user_id =? && employee_id = ?");
		ps1.setInt(1,quizScore.getScore());
		ps1.setString(2, completion_time);
		ps1.setInt(3, 1);
		ps1.setInt(4, quizScore.getArea_of_interest_cat_id());
		ps1.setString(5, quizScore.getQuiz_id());
		ps1.setString(6, String.valueOf(quizScore.getUser().getId()));
		ps1.setString(7, String.valueOf(quizScore.getUser().getEmp_id()));
		
		
		int result  =  ps1.executeUpdate();
		
		ps1.close();
		con1.close();
		
		if(result > 0)
		{
			con1 =  db.getConnection();
			
			if(checkQuizScoreExistence(quizScore))
			{
			    ps1 = con1.prepareStatement("update hb_quiz_score set quiz_id = ?, start_time = ?, completion_time = ?, score = ?, status = ?, area_of_interest_cat_id = ? where user_id =? && employee_id = ?");
			    ps1.setString(1, quizScore.getQuiz_id());
				ps1.setString(2, quizScore.getStart_time());
				ps1.setString(3, completion_time);
				ps1.setInt(4,quizScore.getScore());
				ps1.setInt(5, 1);
				ps1.setInt(6, quizScore.getArea_of_interest_cat_id());
				ps1.setString(7, String.valueOf(quizScore.getUser().getId()));
				ps1.setString(8, String.valueOf(quizScore.getUser().getEmp_id()));
			}else{
				ps1 = con1.prepareStatement("insert into hb_quiz_score(quiz_id, user_id, employee_id, start_time, status, score, completion_time, area_of_interest_cat_id) values(?, ?, ?, ?, ?, ?, ?, ?)");
				ps1.setString(1, quizScore.getQuiz_id());
				ps1.setString(2, String.valueOf(quizScore.getUser().getId()));
				ps1.setString(3, quizScore.getUser().getEmp_id());
				ps1.setString(4, quizScore.getStart_time());
				ps1.setInt(5, 1);
				ps1.setInt(6, quizScore.getScore());
				ps1.setString(7, completion_time);
				ps1.setInt(8, quizScore.getArea_of_interest_cat_id());
			}
			
			
			/*
			UserFirebaseNotificationDAO userFirebaseNotificationDAO = new UserFirebaseNotificationDAO();
			userFirebaseNotificationDAO.sendQuizCompleteNotificationToSpecificUser(tokenId);
			*/
			
			if(tokenId != 0){
				
				UserFirebaseNotificationDAO userFirebaseNotificationDAO = new UserFirebaseNotificationDAO();
				UserFirebaseToken userFirebaseToken = userFirebaseNotificationDAO.getFirebaseTokenBasedOnTokenId(tokenId);
				
				QuizNotificationClient quizNotification = new QuizNotificationClient();
				quizNotification.sendQuizCompleteNotificationToSpecificUser(userFirebaseToken);
			}
			
			
			
		   int result2  = ps1.executeUpdate();
		
		   if(!(result2 > 0))
		   {
			   quizScore =  null;
		   }
		  
	
		   return quizScore;
		}
		
		
		quizScore.setCompletion_time(completion_time);
		return quizScore;
	}
	
	
	//Checking whether the user already attempted the quiz or not
	public boolean checkQuizScoreExistence(QuizScore quizScore)throws ClassNotFoundException, SQLException
	{
		
		Connection       con2 = null;
		PreparedStatement ps2 = null;
		ResultSet         rs2 = null;
		
		con2 = db.getConnection();
		ps2  = con2.prepareStatement("select * from hb_quiz_score where user_id = ? && employee_id = ?");
		ps2.setString(1, String.valueOf(quizScore.getUser().getId()));
		ps2.setString(2, String.valueOf(quizScore.getUser().getEmp_id()));
		rs2  = ps2.executeQuery();
					
		
		int i = 0;
		
		while(rs2.next())
		{
			i++;
		}
		
		
		rs2.close();
		ps2.close();
		con2.close();
				
		if(i == 0){
			return false;
		}
		
		return true;
	}


	//Getting Default Value of a Specific Column
	public String getColumnDefaultValue(Connection def_conn,String TableName,String ColumnName) throws ClassNotFoundException, SQLException
	{
		String columnDefaultVal = "";
		DatabaseMetaData md = def_conn.getMetaData() ;
		ResultSet rs = md.getColumns(def_conn.getCatalog(), md.getUserName(),TableName,ColumnName);
			  
			if(rs.next())
			{
			 columnDefaultVal =  rs.getString("COLUMN_DEF");
			}
			  
		return columnDefaultVal;
	}
	
	//Getting Score Information of User
	public QuizScore getQuizScoreInformation(QuizScore quizScore) throws ClassNotFoundException, SQLException
	{	
		if(checkQuizScoreExistence(quizScore))
		{
			con1 = db.getConnection();
			
			ps1  = con1.prepareStatement("select * from hb_quiz_score where user_id = ? && employee_id = ?");
			ps1.setString(1, String.valueOf(quizScore.getUser().getId()));
			ps1.setString(2, String.valueOf(quizScore.getUser().getEmp_id()));
			rs1  = ps1.executeQuery();
			
			while(rs1.next())
			{
				
				quizScore.setId(rs1.getInt(1));
				quizScore.setQuiz_id(rs1.getString(2));
				quizScore.setScore(rs1.getInt(5));
				quizScore.setStart_time(rs1.getString(6));
				quizScore.setCompletion_time(rs1.getString(7));
				quizScore.setStatus(rs1.getInt(8));
				quizScore.setNo_of_qus(rs1.getInt(9));
				quizScore.setArea_of_interest_cat_id(rs1.getInt(10));
				quizScore.setHighest_score(getHighestScore(quizScore));
				
			}
			
			rs1.close();
			ps1.close();
			con1.close();
		}else{
			quizScore.setId(0);
		}
				
		return quizScore;
	}
	
	//Getting Highest Quiz Score for an existence user
    public int getHighestScore(QuizScore quizScore) throws ClassNotFoundException, SQLException
    {	
    	Connection       con3 = null;
    	PreparedStatement ps3 = null;
    	ResultSet         rs3 = null;
    	
		if(checkQuizScoreExistence(quizScore)){
			con3 = db.getConnection();
			ps3 = con3.prepareStatement("select MAX(score) from `hb_quiz_score_log` where user_id =? && employee_id = ?");
			ps3.setString(1, String.valueOf(quizScore.getUser().getId()));
			ps3.setString(2, quizScore.getUser().getEmp_id());
			rs3 = ps3.executeQuery();
			
			while(rs3.next()){
				quizScore.setHighest_score(rs3.getInt(1));
			}
			
			 rs3.close();
			 ps3.close();
			 con3.close();
			
			return quizScore.getHighest_score();
		}else{
			return 0;
		}
		
		
	}
	
	//Getting Maximum Frequency of a number to know about interest 
/*	public <T> T getMaxFrequentNumber(List<T> interest_list)
	{
		Map<T, Integer> map = new HashMap<>();

	    for (T t : interest_list) {
	        Integer val = map.get(t);
	        map.put(t, val == null ? 1 : val + 1);
	    }

	    Entry<T, Integer> max = null;

	    for (Entry<T, Integer> e : map.entrySet()) {
	        if (max == null || e.getValue() > max.getValue())
	            max = e;
	    }

	    return max.getKey();
	}
	
	public int trialFrequencyMethod(int[] a)
	{
	
		int count = 1, tempCount;
		  int popular = a[0];
		  int temp = 0;
		  for (int i = 0; i < (a.length - 1); i++)
		  {
		    temp = a[i];
		    tempCount = 0;
		    for (int j = 1; j < a.length; j++)
		    {
		      if (temp == a[j])
		        tempCount++;
		    }
		    if (tempCount > count)
		    {
		      popular = temp;
		      count = tempCount;
		    }
		  }
		  return popular;
	}
*/	
	public int getMaxFrequentNumber(ArrayList<Integer> b){
		
		int count = 1, tempCount;
		  int popular = b.get(0);
		  int temp = 0;
		  for (int i = 0; i < (b.size() - 1); i++)
		  {
		    temp = b.get(i);
		    tempCount = 0;
		    for (int j = 1; j < b.size(); j++)
		    {
		      if (temp == b.get(j))
		        tempCount++;
		    }
		    if (tempCount > count)
		    {
		      popular = temp;
		      count = tempCount;
		    }
		  }
		  return popular;
	}
	
	public QuizQuestion interestCategory(QuizQuestion quizQuestion_p) throws ClassNotFoundException, SQLException
	{
		Connection        interest_con = null;
		PreparedStatement interest_ps1 = null;
		ResultSet         interest_rs1 = null;
		
		interest_con = db.getConnection();
		interest_ps1  = interest_con.prepareStatement("select category_id from hb_quiz_question where id = ?");
		interest_ps1.setInt(1, quizQuestion_p.getId());
		interest_rs1  = interest_ps1.executeQuery();
		
		while(interest_rs1.next())
		{
			quizQuestion_p = new QuizQuestion();
			quizQuestion_p.setCategory_id(interest_rs1.getInt(1));
		
		}
		
		interest_rs1.close();
		interest_ps1.close();
		interest_con.close();
		
		return quizQuestion_p;
	}
	
	
}
