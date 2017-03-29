package org.social.heartbeat.service;

import java.io.IOException;
import java.sql.SQLException;

import org.social.heartbeat.bean.CsrInitCategory;
import org.social.heartbeat.bean.SignInUser;
import org.social.heartbeat.bean.UploadedImage;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.dao.UserDAO;
import org.social.heartbeat.bean.UserProfile;



public class UserService 
{
	
	UserDAO userDAO = new UserDAO();
	
	
	//Getting single user details
	public User getSingleUserDetails(int id) throws ClassNotFoundException, SQLException{
		return userDAO.getSingleUserDetails(id);
	}

	
	//Authenticating to User for Login
	public SignInUser userLogin(SignInUser signInUser) throws ClassNotFoundException, SQLException{
		return userDAO.userLogin(signInUser);
	}
	
	//Getting User Profile Complete Information
	public UserProfile getUserProfileInformation(int id) throws ClassNotFoundException, SQLException
	{
		return userDAO.getUserProfileINformation(id);
	}

	//Updating profile information
	public UserProfile updateUserProfile(UserProfile userProfile) throws ClassNotFoundException, SQLException
	{
		return userDAO.updateUserProfile(userProfile);
	}

	//Getting Area of Interest Based on User
    public CsrInitCategory getCsrInitCatDataByUser(User user) throws ClassNotFoundException, SQLException{
    	return userDAO.getCsrInitCatDataByUser(user);
    }
}
