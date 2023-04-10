package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone)
	{
		User up = new User();
		
		up.setId(Integer.parseInt(UserID));
		up.setUsername(UserName);
		up.setFirstName(FirstName);
		up.setLastName(LastName);
		up.setEmail(Email);
		up.setPassword(Password);
		up.setPhone(Phone);
		
		Response response = UserEndPoints.createUser(up);		
		Assert.assertEquals(response.getStatusCode(), 200); 
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDelUserByName(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
