package org.social.heartbeat.util;

public class WebConfigURI 
{
	//running on hosted server
	//public static String base_uri        = "https://socialheartbeat-bloggie.rhcloud.com/api/";
	
	//running on local
	public static String base_uri        = "http://localhost:8080/socialheartbeat/api/";
	 
	public static String quiz_cmplt_uri  =  base_uri + "notify/sendQuizCompleteNotification";
	
	
	
	public static String firebase_notification_uri = "https://fcm.googleapis.com/fcm/send";
	public static String get_address_info_uri      = "http://maps.googleapis.com/maps/api/geocode/json?";
	
	

	
}
