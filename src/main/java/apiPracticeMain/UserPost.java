package apiPracticeMain;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import pojo.UserDetail;
import pojo.UserDetailsResponse;

public class UserPost {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://reqres.in";
		
		UserDetail ud = new UserDetail();
		ud.setName("morpheus");
		ud.setJob("leader");
	
		Response response = given().header("Content-Type", "application/json").body(ud).
				when().post("/api/users").then().assertThat().statusCode(201).extract().response();
		
		       ObjectMapper om = new ObjectMapper();
		              UserDetailsResponse udr = om.readValue(response.asString(), UserDetailsResponse.class);
		              
		              System.out.println("User ID :" +udr.getId());
		              System.out.println("User Name :" +udr.getName());
		              System.out.println("User Job :" +udr.getJob());
		              System.out.println("UserCreatedDate :" +udr.getCreatedAt());
		              
		              
	}

}
