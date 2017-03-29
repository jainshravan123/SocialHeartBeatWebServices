package org.social.heartbeat.client;

import java.sql.SQLException;

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

public class FirebaseNotificationClient {


	public FirebaseNotificationResp sendQuizCompleteNotificationToSpecificUser(UserFirebaseToken userFirebaseToken_p)  throws ClassNotFoundException, SQLException, JSONException
	{
        String url = WebConfigURI.firebase_notification_uri;
        
        JSONObject innerJsonObject = new JSONObject();
        innerJsonObject.put("title", "Social Heart Beat");
        innerJsonObject.put("text", "Your quiz has submitted successfully.");
		
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notification", innerJsonObject);
        jsonObject.put("to", userFirebaseToken_p.getToken());
		
        System.out.println("::::: Sending Message Object ::::");
        System.out.println(jsonObject.toString());
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        Client restClient = Client.create(defaultClientConfig);
        
        
        WebResource webResource = restClient.resource(url);
        
        
        ClientResponse resp = webResource
        		 					.type(MediaType.APPLICATION_JSON)
                                    .header("Authorization","key=AIzaSyAP8ilRQUTq_b3C3U_XuMbiA8wWdtaL9ZY")
                                    .post(ClientResponse.class, jsonObject.toString());
        
        // create JsonProvider to provide custom ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.setMapper(mapper);
        
        
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);
        System.out.println("response: "+output);
        
        JSONObject jsonOutput = new JSONObject(output);
        userFirebaseToken_p.setSuccess(jsonOutput.getInt("success"));
        
        FirebaseNotificationResp firebaseNotificationResp = new FirebaseNotificationResp();
        JSONObject json_output_resp = new JSONObject(output);
        firebaseNotificationResp.setSuccess(json_output_resp.getInt("success"));
        firebaseNotificationResp.setFailure(json_output_resp.getInt("failure"));
        firebaseNotificationResp.setMulticast_id(json_output_resp.getString("multicast_id"));
		
		return firebaseNotificationResp;
	}
}
