package com.techelevator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Food;

@Configuration
@EnableWebMvc  // the functionality of this is similar to <mvc:annotation-driven /> // 
//if we want to discard Spring Boot’s MVC features and declare a custom configuration, we can do so by using
@Controller
public class ApiController {

	@RequestMapping("/home")
	public String wasteTime(@RequestParam String searchParam, HttpServletRequest request) throws JsonProcessingException, IOException {
		
		// 1. Construct the request
		
		String appID = "e81f420a";
		String appKey = "f12b31fc563b61bd6b136089772ea95a";
	
		String apiURL = "https://api.nutritionix.com/v1_1/search/"; /// Still need to verify
//		String keyValue = "\r\n" + "f12b31fc563b61bd6b136089772ea95a";/// Nutritionix Key
//		String exchange = apiURL + "api_key=" + keyValue + "&q=" + searchParam ;
		String exchange = apiURL+searchParam+"?";
		
		
		// 2. Helper objects you need, look at the import section a well:
		HttpEntity<String> httpEntity = new HttpEntity<>("");
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		// 3. The .exchange method makes the call, send the request from step 1.
		ResponseEntity<String> response = restTemplate.exchange(exchange, HttpMethod.GET, httpEntity, String.class);		
		
		
		// 4. Read the response into a format I can understand.
		JsonNode jsonNode = objectMapper.readTree(response.getBody());
		
		
		
		
		// 5. Parse the data to get what you want
		// The path method applied to a JsonNode gets you to a node on a tree, you can chain them too!
		// In this case want to loop up to the limit.	
		System.out.println(jsonNode.path("list").size());
		System.out.println(jsonNode.path("list").path(0).path("q"));
		//System.out.println(jsonNode.path("list").path(0).path("slug"));
		
		
		// 6. Let's try something more complicated, let's loop through all the nodes and pick up the
		// gifs associated with each node. The total number of nodes depends on the "limits" parameter.
		
		
		Food food = new Food();
		
		
		for (int i =0; i < jsonNode.path("data").size(); i++) {
			System.out.println(jsonNode.path("data").path(i).path("q"));
			
			
		}
		

		
		return "home";
	}
}
