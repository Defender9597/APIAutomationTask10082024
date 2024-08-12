package apiPracticeMain;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import pojo.Data;
import pojo.GetUsers;


public class Users {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
	RestAssured.baseURI = "https://reqres.in";
	Response response = RestAssured.given().queryParam("page", 2).
			when().get("/api/users").then().assertThat().statusCode(200).extract().response();
	
	ObjectMapper om = new ObjectMapper();
	GetUsers gu = om.readValue(response.asString(), GetUsers.class);
	
	System.out.println("Getting the page number :" +gu.getPage());
	System.out.println("Getting the Per page number :" +gu.getPer_page());
	System.out.println("Getting the total number :" +gu.getTotal());
	System.out.println("Getting the Totalpage number :" +gu.getTotal_pages());
	
	System.out.println("Getting URL :" +gu.getSupport().getUrl());
	System.out.println("Getting Text :" +gu.getSupport().getText());
	List<Data> getData = gu.getData();
	for (int i=0; i<getData.size();i++)
	{
		System.out.println("GettingID from Data List :"+getData.get(i).getId());
		System.out.println("Getting Email from Data List :"+getData.get(i).getEmail());
		System.out.println("Getting Firstname from Data List :"+getData.get(i).getFirst_name());
		System.out.println("Getting LastName from Data List :"+getData.get(i).getLast_name());
		System.out.println("Getting avatar from Data List :"+getData.get(i).getAvatar());
		System.out.println();
	}
	
}

}