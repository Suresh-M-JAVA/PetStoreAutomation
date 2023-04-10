package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints2 {

	//getting url from properties file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");  
		return routes;
		
	}
	
	public static Response createUser(User payload) {
		
	String posturl	= getUrl().getString("post_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(posturl);
		
		return response;
	}
	
public static Response readUser(String userName) {
		
	String geturl	= getUrl().getString("get_url");
		Response response=given()
			.pathParam("username", userName)
		.when()
			.get(geturl);
		
		return response;
	}

public static Response updateUser(String userName, User payload) {
	
	String updateurl	= getUrl().getString("update_url");
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
	.when()
		.put(updateurl);
	
	return response;
}

public static Response deleteUser(String userName) {
	
	String deleteurl	= getUrl().getString("delete_url");
	Response response=given()
		.pathParam("username", userName)
	.when()
		.delete(deleteurl);
	
	return response;
}

}
