package com.techelevator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.Forecast;
import com.techelevator.npgeek.model.ForecastDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.ReviewDao;
import com.techelevator.npgeek.model.jdbc.JdbcParkDao;

@Controller
public class ParkController {

	@Autowired
	ParkDao parkDao;

	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showParks(HttpServletRequest request) {
		List<Park> parks = parkDao.getAllParks();
		request.setAttribute("parks", parks);
		return "Homepage";
		}
	
	@Autowired
	ForecastDao forecastDao;
	
	@RequestMapping(path = "/detail", method = RequestMethod.GET)
	public String showParkDetail(HttpServletRequest request,
			@RequestParam String code) {
		Park park = parkDao.getById(code.toUpperCase());
		request.setAttribute("park", park);

		Forecast[] forecast = forecastDao.getParkWeather(code.toUpperCase());
		request.setAttribute("forecasts", forecast);
		//request.setAttribute("urlName", forecast.);
		return "detail";
	}
	
	@RequestMapping(path="/detail", method=RequestMethod.POST)
	public String changeTempDetail(HttpServletRequest request,
			@RequestParam String code,
			@RequestParam String scale) {
		Park park = parkDao.getById(code.toUpperCase());
		request.setAttribute("park", park);
		Forecast[] forecast = forecastDao.getParkWeather(code.toUpperCase());
		request.setAttribute("forecasts", forecast);
		request.setAttribute("scale", scale);
		return "detail";
	}
	
	@RequestMapping(path="/review")
	public String showReviewForm(HttpServletRequest request) {
		List<Park> parks = parkDao.getAllParks();
		request.setAttribute("parks", parks);
		return "review";
	}
	
	@Autowired
	ReviewDao reviewDao;
	
	@RequestMapping(path="/reviewresults", method=RequestMethod.GET)
	public String showReviewResults(HttpServletRequest request
			) {
		List<Park> results = reviewDao.showFavPark(parkDao);
		request.setAttribute("results", results);
		return "reviewresults";
	}
	
	@RequestMapping(path="/reviewresults", method=RequestMethod.POST)
	public String showReviews(
			/*
			 * @Valid @ModelAttribute Registration confirmation, BindingResult result,
			 * RedirectAttributes flash,
			 */
			HttpServletRequest request,
			@RequestParam String parkCode,
			@RequestParam String email,
			@RequestParam String state,
			@RequestParam String activity
			) {
		/*
		 * flash.addFlashAttribute("register", confirmation); if(result.hasErrors()) {
		 * flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "register", result);
		 * return "redirect:/review"; }
		 */
		if (email.contains("@") && (email.contains("."))) {
			reviewDao.saveReview(parkCode, email, state, activity);
			return "redirect:/reviewresults";
		}
		else {
			return "redirect:/review";
		}
	}
}