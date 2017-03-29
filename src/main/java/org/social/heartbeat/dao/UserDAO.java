package org.social.heartbeat.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.social.heartbeat.bean.CsrInitCatData;
import org.social.heartbeat.bean.CsrInitCategory;
import org.social.heartbeat.bean.SignInUser;
import org.social.heartbeat.bean.UploadedImage;
import org.social.heartbeat.bean.User;
import org.social.heartbeat.bean.UserProfile;
import org.social.heartbeat.bean.UserStatus;
import org.social.heartbeat.util.DBConnection;


public class UserDAO
{

	Connection       con1 = null;
	PreparedStatement ps1 = null;
	ResultSet         rs1 = null;
	
	DBConnection db = new DBConnection();
	
	
	//Getting Single User Details
	public User getSingleUserDetails(int id) throws ClassNotFoundException, SQLException{
		
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_user where id = ?");
		ps1.setInt(1, id);
		rs1  = ps1.executeQuery();
		
		User user = new User();
		UserStatus userStatus = new UserStatus();
		
		while(rs1.next())
		{
			user.setId(rs1.getInt(1));
			user.setUsername(rs1.getString(2));
			user.setEmp_id(rs1.getString(3));
			user.setFirstname(rs1.getString(4));
			user.setLastname(rs1.getString(5));
			user.setEmail(rs1.getString(6));
			userStatus.setId(rs1.getInt(7));
		    user.setUserStatus(userStatus);
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return user;
	}
	
	//Authenticating to User for Login
	public SignInUser userLogin(SignInUser signInUser) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_sign_in where username = ? AND password = ?");
		ps1.setString(1, signInUser.getUsername());
		ps1.setString(2, signInUser.getPassword());
		rs1  = ps1.executeQuery();
		
		
		int flag = 0;
		
		while(rs1.next())
		{
			flag = flag + 1;
			
			signInUser.setId(rs1.getInt(1));	
			signInUser.setEmp_id(rs1.getString(3));
			signInUser.setSuccess(1);
			//user.setId(rs1.getInt(2));
			//user.setEmp_id(rs1.getString(3));
			
			//signInUser.setUser(user);
		}
		
		if(flag == 0){
			signInUser.setSuccess(0);
			signInUser.setErrMsg("Wrong Credentials");
		}else{
			signInUser.setSuccess(1);
		}
		rs1.close();
		ps1.close();
		con1.close();
	
		return signInUser;
	}

	
	
	//Getting all of the profile information
	public UserProfile getUserProfileINformation(int id) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("select * from hb_user_profile where id = ?");
		ps1.setInt(1, id);
		rs1  = ps1.executeQuery();
		
		
		UserProfile  userProfile = new UserProfile();
		
		while(rs1.next())
		{
			userProfile.setId(rs1.getInt(1));
	        userProfile.setUser_id(rs1.getInt(2));
			userProfile.setEmp_id(rs1.getString(3));
			userProfile.setFirst_name(rs1.getString(4));
			userProfile.setLast_name(rs1.getString(5));
			userProfile.setHome_contact_number(rs1.getString(6));
			userProfile.setHome_country_code(rs1.getString(7));
			userProfile.setWork_contact_number(rs1.getString(8));
			userProfile.setWork_country_code(rs1.getString(9));
			userProfile.setOfficial_email(rs1.getString(10));
			userProfile.setPersonal_email(rs1.getString(11));
			userProfile.setResedential_add(rs1.getString(12));
			userProfile.setOffc_add(rs1.getString(13));
			userProfile.setProfile_image(rs1.getString(14));
			userProfile.setProfile_back_image(rs1.getString(15));
		
	
		}
		
		rs1.close();
		ps1.close();
		con1.close();
		
		return userProfile;
	}
	
	
	//Updating profile information
	public UserProfile updateUserProfile(UserProfile userProfile) throws ClassNotFoundException, SQLException
	{
		con1 = db.getConnection();
		ps1  = con1.prepareStatement("update hb_user_profile set first_name = ?, lastname=?, home_contact_number=?, home_country_code=?,  work_contact_number=?, work_country_code=?, personal_email=?, residential_add=? where id=? && emp_id=?");
		ps1.setString(1, userProfile.getFirst_name());
		ps1.setString(2, userProfile.getLast_name());
		ps1.setString(3, userProfile.getHome_contact_number());
		ps1.setString(4, userProfile.getHome_country_code());
		ps1.setString(5, userProfile.getWork_contact_number());
		ps1.setString(6, userProfile.getWork_country_code());
		ps1.setString(7, userProfile.getPersonal_email());
		ps1.setString(8,  userProfile.getResedential_add());
		ps1.setInt(9, userProfile.getId());
		ps1.setString(10, userProfile.getEmp_id());
		int result  = ps1.executeUpdate();
	
		ps1.close();
		con1.close();
		
		if(!(result>0)){
			return null;
		}
	
		return userProfile;
	}
	
	//Updating Profile Picture
	public UploadedImage updateUserProfilePicture(UploadedImage uploadedImage) throws ClassNotFoundException, SQLException, IOException
	{
		con1 = db.getConnection();
	
		byte[] imageByteArray = Base64.decodeBase64(uploadedImage.getImage()); 
		 
        // Write Image into File system - Make sure you update the path
        FileOutputStream imageOutFile = new FileOutputStream(uploadedImage.getImage_name());
        imageOutFile.write(imageByteArray);

        imageOutFile.close();
        
        String path1  = new File("").getAbsolutePath();
        System.out.println("PATH : "+path1);
        
      
     
        return uploadedImage;
	}
	
	//Getting Area of Interest Based on User
    public CsrInitCategory getCsrInitCatDataByUser(User user) throws ClassNotFoundException, SQLException{
			con1 = db.getConnection();
			ps1  = con1.prepareStatement("SELECT t2.id, t2.cat_name FROM `hb_quiz_score` AS `t1`, `hb_csr_init_category` AS `t2` WHERE t1.area_of_interest_cat_id = t2.id AND t1.user_id =? AND t1.employee_id=?");
			ps1.setInt(1, user.getId());
			ps1.setString(2, user.getEmp_id());
			rs1  = ps1.executeQuery();
			
			ArrayList<CsrInitCatData> csrInitCatDataList = new ArrayList<CsrInitCatData>();
			
			CsrInitCategory csrInitCategory = new CsrInitCategory();
			
			while(rs1.next())
			{
				
				
				csrInitCategory.setId(rs1.getInt(1));
				csrInitCategory.setCat(rs1.getString(2));

			}
			
			rs1.close();
			ps1.close();
			con1.close();
			
			return csrInitCategory;
	}
	
}
