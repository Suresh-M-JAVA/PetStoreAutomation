package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {

	Faker fk; 
	User userPayload; 
	
	public Logger logger;
	
	@BeforeClass 
	public void setupData() {
		fk = new Faker();
		userPayload = new User();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password());
		userPayload.setPhone(fk.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1) 
	public void testPostUser()
	{
		logger.info("***********creating user by routes properties*************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200); 
		logger.info("***********user created*************");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() 
	{
		logger.info("***********reading user info*************");
		Response response = UserEndPoints2.readUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********user info displayed*************");
	}
	
	@Test(priority = 3) 
	public void testUpdateUserByName()
	{
		logger.info("***********updating user*************");
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200); 
		logger.info("***********user updated*************");
		//checking data after update
		Response responseafterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(responseafterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() 
	{
		logger.info("***********deleting user*************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***********user deleted*************");
	}
	
}
