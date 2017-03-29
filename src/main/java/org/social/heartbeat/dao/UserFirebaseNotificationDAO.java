package org.social.heartbeat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.FirebaseNotificationResp;
import org.social.heartbeat.bean.UserFirebaseToken;
import org.social.heartbeat.util.DBConnection;
import org.social.heartbeat.util.WebConfigURI;
import org.social.heartbeat.client.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import org.json.JSONException;
//import org.json.JSONObject;



public class UserFirebaseNotificationDAO
{
	
	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	//Registers Token of user
	public UserFirebaseToken registerFirebaseToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		con1 =  db.getConnection();
		String generatedColumns[] = {"id"};
		ps1  = con1.prepareStatement("insert into hb_user_token(user_id, token, device_mac_id) values(?, ?, ?)", generatedColumns);
		ps1.setInt(1, userFirebaseToken.getUser_id());
		ps1.setString(2, userFirebaseToken.getToken());
		ps1.setString(3, userFirebaseToken.getMac_id());
	
		
		
	    int result  = ps1.executeUpdate();
	    
	    rs1 = ps1.getGeneratedKeys();
	    
	    
	    if(result > 0 )
	    {
		    while(rs1.next())
		    {
		    	userFirebaseToken.setId(rs1.getInt(1));
		    	userFirebaseToken.setSuccess(1);
		    }
	    }
	    
	    rs1.close();
	    ps1.close();
		con1.close();
		
		return userFirebaseToken;
	}
	
	
	//Registers Token Before SignIn
	public UserFirebaseToken registerFirebaseTokenBeforeSignin(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		con1 =  db.getConnection();
		String generatedColumns[] = {"id"};
		ps1  = con1.prepareStatement("insert into hb_user_token(user_id, token, device_mac_id) values(?, ?, ?)", generatedColumns);
		ps1.setInt(1, userFirebaseToken.getUser_id());
		ps1.setString(2, userFirebaseToken.getToken());
		ps1.setString(3, userFirebaseToken.getMac_id());
		
		
	    int result  = ps1.executeUpdate();
	    
	    rs1 = ps1.getGeneratedKeys();
	    
	    
	    if(result > 0 )
	    {
		    while(rs1.next())
		    {
		    	userFirebaseToken.setId(rs1.getInt(1));
		    	userFirebaseToken.setSuccess(1);
		    }
	    }
	    
	    rs1.close();
	    ps1.close();
		con1.close();
		
		return userFirebaseToken;
	}
	
	//Update UserId After SignIn
	public UserFirebaseToken updateUserIdAccorToken(UserFirebaseToken userFirebaseToken) throws SQLException, ClassNotFoundException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("update hb_user_token set user_id = ? where token = ? && device_mac_id = ?");
		ps1.setInt(1, userFirebaseToken.getUser_id());
		ps1.setString(2, userFirebaseToken.getToken());
		ps1.setString(3, userFirebaseToken.getMac_id());
	
		int result  = ps1.executeUpdate();
	
		ps1.close();
		con1.close();
		
		if(!(result>0)){
			return null;
		}
	
		userFirebaseToken.setSuccess(1);
		return userFirebaseToken;
	}
	
	
	//Get stored firebase token
	public UserFirebaseToken getFirebaseTokenBasedOnTokenId(int tokenId) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_user_token where id=?");
		ps1.setInt(1, tokenId);
		rs1  = ps1.executeQuery();
		
		UserFirebaseToken userFirebaseToken = new UserFirebaseToken();
		
		while(rs1.next())
		{
			userFirebaseToken.setId(rs1.getInt(1));
			userFirebaseToken.setUser_id(rs1.getInt(2));
			userFirebaseToken.setToken(rs1.getString(3));
			userFirebaseToken.setMac_id(rs1.getString(4));
		}
		
		return userFirebaseToken;
	}
	
	
	//send quiz complete notification to specific user
	public FirebaseNotificationResp sendQuizCompleteNotificationToSpecificUser(UserFirebaseToken userFirebaseToken_p) throws ClassNotFoundException, SQLException, JSONException
	{
	
		FirebaseNotificationClient firebaseNotificationClient = new FirebaseNotificationClient();
		FirebaseNotificationResp firebaseNotificationResp = firebaseNotificationClient.sendQuizCompleteNotificationToSpecificUser(userFirebaseToken_p);
		return firebaseNotificationResp;
		
	}
}
