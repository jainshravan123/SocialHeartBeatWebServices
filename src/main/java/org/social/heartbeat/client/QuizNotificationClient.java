package org.social.heartbeat.client;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.social.heartbeat.bean.FirebaseNotificationResp;
import org.social.heartbeat.bean.UserFirebaseToken;
import org.social.heartbeat.util.WebConfigURI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class QuizNotificationClient {
	
	public FirebaseNotificationResp sendQuizCompleteNotificationToSpecificUser(UserFirebaseToken userFirebaseToken) throws JSONException
	{
		    String uri = WebConfigURI.quiz_cmplt_uri;
	         
	        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
	        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);  
	        Client restClient = Client.create(defaultClientConfig);
	        
	        WebResource webResource = restClient.resource(uri);
	        
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("token", userFirebaseToken.getToken());
	        System.out.println("Token : "+userFirebaseToken.getToken());
	        System.out.println("Token Object : "+jsonObject.toString());
	        
	        ClientResponse resp = webResource
	        		 					.type(MediaType.APPLICATION_JSON)
	                                    .post(ClientResponse.class, jsonObject.toString());
	        
	        
	        System.out.println("Resp : "+ resp.toString());
	        
	        if(resp.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = resp.getEntity(String.class);
	        System.out.println("response: "+output);
	        
	        FirebaseNotificationResp firebaseNotificationResp = new FirebaseNotificationResp();
	        JSONObject jsonOutput = new JSONObject(output);
	        firebaseNotificationResp.setSuccess(jsonOutput.getInt("success"));
	        firebaseNotificationResp.setFailure(jsonOutput.getInt("failure"));
	        firebaseNotificationResp.setMulticast_id(jsonOutput.getString("multicast_id"));
	        
	        return firebaseNotificationResp;
	}
}
