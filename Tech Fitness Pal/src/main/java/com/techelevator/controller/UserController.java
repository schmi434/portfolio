package com.techelevator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.model.UserProfile;
import com.techelevator.model.UserProfileDAO;
import com.techelevator.model.meal;
import com.techelevator.model.mealDAO;

@Controller
public class UserController {

	private UserDAO userDAO;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Autowired
	UserProfileDAO userprofileDao;
	
	@Autowired
	mealDAO mealDao;

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}
		
		userDAO.saveUser(user.getUserName(), user.getPassword());
		return "redirect:/login";
	}
	
	@RequestMapping(path="/profile", method=RequestMethod.GET)
	public String displayUserDashboard(HttpSession session) {
//		User x = (User) session.getAttribute("currentUser");
//		System.out.println(x.getUserName());
		return "/Profile";
	}
	
	@RequestMapping(path="/foodsearch", method=RequestMethod.GET)
	public String displayHome() {
//		User x = (User) session.getAttribute("currentUser");
//		System.out.println(x.getUserName());
		return "foodsearch";
	}
	
	
	@RequestMapping(path="/profile", method=RequestMethod.POST)
	public String updateUserDashboard(
			@RequestParam String displayName, 
			@RequestParam double startingWeight,
			@RequestParam double goalWeight,
			@RequestParam int age, 
			@RequestParam double height,
			HttpSession session
			) {
	
	User x = (User) session.getAttribute("currentUser"); // This gets us the username for the session 
	String userX = x.getUserName();
		
		UserProfile userprofile = new UserProfile();
		userprofile.setUserName(userX);
		userprofile.setDisplayName(displayName);
		userprofile.setStartingWeight(startingWeight);
	    userprofile.setGoalWeight(goalWeight);
	    userprofile.setAge(age);
	    userprofile.setHeight(height);

	    session.setAttribute("profile", userprofile);
	    
	    userprofileDao.saveProfile(userprofile.getUserName(), userprofile.getDisplayName(), userprofile.getStartingWeight(), userprofile.getCurrentWeight(), 
			userprofile.getGoalWeight(), userprofile.getAge(), userprofile.getHeight(), userprofile.getAvatarFileName(), session);
	    return "foodsearch";	
	}
	
	@RequestMapping(path="/foodsearch", method=RequestMethod.POST)
	public String enterMeal(
			@RequestParam String mealLabel, 
			@RequestParam String foodEaten,
			@RequestParam int calories,
			@RequestParam Date date,
			HttpSession session
			) {
				
			User x = (User)session.getAttribute("currentUser"); 
			String userX = x.getUserName();
		
			meal newMeal = new meal();
			newMeal.setMealLabel(mealLabel);
			newMeal.setFoodeaten(foodEaten);
			newMeal.setCalories(calories);
			newMeal.setDate(date);
			
			mealDao.saveMeal(newMeal.getMealLabel(), newMeal.getFoodeaten(), newMeal.getCalories(), newMeal.getDate(), userX);
			
			return "foodsearch";
	}
	
	@RequestMapping(path="/displayprogress", method=RequestMethod.GET)
	public String showCals(HttpSession session) {
		
	
		ArrayList<String> calsList = mealDao.showDailyCals(session);
		
	
		session.setAttribute("day", calsList);
		

		
		return "displayprogress";
	}
	
}
