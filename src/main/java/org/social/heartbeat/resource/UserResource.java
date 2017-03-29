package org.social.heartbeat.resource;


import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.social.heartbeat.bean.CsrInitCategory;
import org.social.heartbeat.bean.SignInUser;
import org.social.heartbeat.bean.UploadedImage;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.bean.UserProfile;
import org.social.heartbeat.service.UserService;




@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource 
{

	UserService userService = new UserService();
	
	@GET
	@Path("/{userId}")
	public User getSingleUserDetails(@PathParam("userId") int userId) throws ClassNotFoundException, SQLException{
		return userService.getSingleUserDetails(userId);
	}
	
	
	@POST
	@Path("/login")
	public SignInUser userLogin(SignInUser signInUser) throws ClassNotFoundException, SQLException{
		return userService.userLogin(signInUser);
	}

	
	@GET
	@Path("/profile/{userId}")
	public UserProfile getUserProfileInformation(@PathParam("userId") int userId) throws ClassNotFoundException, SQLException
	{
		return userService.getUserProfileInformation(userId);
	}


    @PUT
	@Path("/profile/updateProfile")
	public UserProfile updateUserProfile(UserProfile userProfile) throws ClassNotFoundException, SQLException
	{
		return userService.updateUserProfile(userProfile);
	}

    @POST
    @Path("/area_of_interest")
    public CsrInitCategory getCsrInitCatDataByUser(User user) throws ClassNotFoundException, SQLException{
    	return userService.getCsrInitCatDataByUser(user);
    }
	
}
