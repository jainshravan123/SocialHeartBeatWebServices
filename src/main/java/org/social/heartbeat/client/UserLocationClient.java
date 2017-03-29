package org.social.heartbeat.client;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.social.heartbeat.bean.UserLocation;
import org.social.heartbeat.util.WebConfigURI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserLocationClient {

	public UserLocation getUserLocationByLatLong(UserLocation userLocation_p) throws JSONException
	{
		
        String uri = WebConfigURI.get_address_info_uri + "latlng=" + userLocation_p.getLatitude() +","+ userLocation_p.getLongitude() + "&sensor=false";
        //http://maps.googleapis.com/maps/api/geocode/json?latlng=12.9685,77.7094&sensor=false
        
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        Client restClient = Client.create(defaultClientConfig);
        
        WebResource webResource = restClient.resource(uri);
        
        ClientResponse resp = webResource
        		 					.type(MediaType.APPLICATION_JSON)
                                    .get(ClientResponse.class);
        
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);
        System.out.println(":::::: Location Response  :::::::::");
        //System.out.println(output);
        
        
        UserLocation userLocation  = new UserLocation();
        JSONObject jsonObject1 = new JSONObject(output);
        JSONArray jsonArray1 =  jsonObject1.getJSONArray("results");
        JSONObject jsonObject2 = (JSONObject) jsonArray1.get(0);
        JSONArray jsonArray2 = jsonObject2.getJSONArray("address_components");
        
        for(int i=0; i< jsonArray2.length(); i++){
        	
        	JSONObject jsonObject3 =(JSONObject) jsonArray2.get(i);
        	JSONArray jsonArray4 = jsonObject3.getJSONArray("types");
        	
        	for(int j=0; j<jsonArray4.length(); j++){
        		if((jsonArray4.get(j).toString()).equals("locality")){
        			userLocation.setCity(jsonObject3.getString("long_name"));
        		}
        		
        		if((jsonArray4.get(j).toString()).equals("administrative_area_level_2")){
        			
        		}
        		
        		if((jsonArray4.get(j).toString()).equals("country")){
        			userLocation.setCountry(jsonObject3.getString("long_name"));
        		}
        	}
        	
        }
       
		userLocation_p.setCity(userLocation.getCity());
		userLocation_p.setCountry(userLocation.getCountry());
		
		return userLocation_p;
	}
}
