package com.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.activity.Activity;
import com.domain.activity.ActivityContent;
import com.domain.activity.TouristPackage;
import com.domain.finance.Discount;
import com.domain.people.Role;
import com.domain.people.TouristDelete;
import com.domain.people.User;
import com.service.activity.ActivityContentService;
import com.service.activity.ActivityService;
import com.service.activity.HomeRoomService;
import com.service.activity.HomeService;
import com.service.activity.NotificationService;
import com.service.activity.TentService;
import com.service.activity.TouristPackageService;
import com.service.finance.DiscountService;
import com.service.finance.PaymentService;
import com.service.people.TouristService;
import com.service.people.UserService;
import com.service.people.UserServiceImpl;


@Controller
public class UserController {

	private UserService userService;
	private ActivityContentService contentService;
	private ActivityService activityService;
	private HomeService homeService;
	private TentService tentService;
	private HomeRoomService homeRoomService;
	
	private TouristPackageService touristPackageService;
	private TouristService touristService;
	private DiscountService discountService;
	private PaymentService paymentService;
	
	private NotificationService notificationService;
	
	private String profession, nationality;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Autowired
	public void setTouristService(TouristService touristService) {
		this.touristService = touristService;
	}
	
	@Autowired
	public void setTouristPackageService(TouristPackageService touristPackageService) {
		this.touristPackageService = touristPackageService;
	}

	@Autowired
	public void setUserService(UserService uservice) {
		this.userService = uservice;
	}
	
	@Autowired
	public void setContentService(ActivityContentService contentService) {
		this.contentService = contentService;
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	@Autowired
	public void setTentService(TentService tentService) {
		this.tentService = tentService;
	}

	@Autowired
	public void setHomeRoomService(HomeRoomService homeRoomService) {
		this.homeRoomService = homeRoomService;
	}
	
	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	
	//USER ACCESSING SYSTEM


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	@RequestMapping("/")
	public String home() {
		return "/user/homeForUsers";
	}
	
	@RequestMapping("/user/userHome")
	public String userHome() {
		return "/user/homeForUsers";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	
	//USER MANAGEMENT
	
	
	@RequestMapping("/user/registerUser")
	public String showRegisterUser(Model model) {
		model.addAttribute("user", new User());
		return "user/userRegistration";
	}
	
	
	@RequestMapping("/user/saveUser")
		public String saveUser(User user, Model model) {
		Role role= new Role("ROLE_Test");
		//user.setRoles(Arrays.asList(role));
		userService.saveUser(user);
		
		try {
			notificationService.sendNotification(user);
		} catch (Exception e) {
			logger.info("Failed to Send EMail "+e.getMessage()+ user.getUsername());
		}
		model.addAttribute("user", user);
		return "login";
	}

	// USER ACTIVITIES
	
	@RequestMapping("/user/packages")
	public String showPackages(Model model, Principal p) {
		User activeUser= activeUser(p);
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		model.addAttribute("contents", contentService.findAllContents());
		model.addAttribute("homes", homeService.listOfHomes());
		model.addAttribute("tents", tentService.listOfTents());
		model.addAttribute("packages", touristPackageService.listOfUserPackages(activeUser));
		model.addAttribute("tourPackage", new TouristPackage());
		model.addAttribute("tourist", new TouristDelete());
		return "user/packages";
 	}
	
	@RequestMapping("/user/activities")
	public String showActivities(Model model, Principal p) {
		User activeUser= activeUser(p);
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		model.addAttribute("contents", contentService.findAllContents());
		model.addAttribute("homes", homeService.listOfHomes());
		model.addAttribute("tents", tentService.listOfTents());
		model.addAttribute("activities", activityService.findPaginatedActivities(1, 1));
		model.addAttribute("tourPackage", new TouristPackage());
		return "user/activities";
	}
	
	@RequestMapping("/user/payments")
	public String showHome(Model model, Principal p) {
		User activeUser= activeUser(p);
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		model.addAttribute("payments", paymentService.listOfPayments());
		return "user/payments";
	}
	
	@RequestMapping("/user/savePackage")
	public String savePackage(Model model, TouristPackage touristPackage, Principal p) {
		User activeUser= activeUser(p);
		touristPackage.setPackageOwner(activeUser);
		touristPackageService.saveTourPackage(touristPackage);
		model.addAttribute("packages", touristPackageService.listOfUserPackages(activeUser));
		model.addAttribute("tourPackage", new TouristPackage());
		model.addAttribute("tourist", new TouristDelete());
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/packages";
	}
	
	private User activeUser(Principal principal) {
		UserServiceImpl actUser =  (UserServiceImpl) ((Authentication)principal).getPrincipal();
		String id= actUser.getUsername();
		 User activeUser = userService.findUser(id);
		 return activeUser;
	}
	
	@RequestMapping("/user/registerTourist/{id}")
	public String showTourist(Principal p, TouristDelete tourist, @PathVariable(value = "id")Long id, Model model) {
		User activeUser= activeUser(p);
		TouristPackage tourPackage= touristPackageService.findTourPackage(id);
		model.addAttribute("tourPackage", tourPackage);
		model.addAttribute("tourist", new TouristDelete());
		model.addAttribute("tourists", touristService.lisOfTourists());
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/myTourists";
	}
	
	@RequestMapping("/user/saveTourist/{id}")
	public String saveTourist(Principal p, Model model, TouristDelete tourist, @PathVariable(value = "id")Long id) {
		User activeUser= activeUser(p);
		TouristPackage tourPackage= touristPackageService.findTourPackage(id);
		tourist.setTourPackage(tourPackage);
		Discount discount = personalDiscount(tourist.getProfession(), tourist.getNationality());
		tourist.setPersonalDiscount(discount);
		touristService.saveTourist(tourist);
		model.addAttribute("tourist", new TouristDelete());
		model.addAttribute("tourPackage", tourPackage);
		model.addAttribute("tourists", touristService.lisOfTourists());
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/myTourists";
	}
	
	public Discount personalDiscount(String profession, String nationality) {
		Discount discount = new Discount();
		
		if((profession.contains("Student") ||profession.contains("Teacher")) && nationality.contains("Rwandan")){
			 discount= discountService.findDiscount("Student");
		} else if(nationality.contains("Rwanda") && ((profession!="Student") || (profession!="Teacher"))) {
			discount= discountService.findDiscount("Rwandan");
		}else if(nationality.contains("Kenya") || nationality.contains("Burundi")||
				nationality.contains("South Sudan")||nationality.contains("Tanzania")
				||nationality.contains("Uganda")) {
			 discount= discountService.findDiscount("EAC");
		} else if(nationality!="Rwandan"){
			discount = discountService.findDiscount("Other");
		} 
		
		return discount;
	}
	
	@RequestMapping("/user/tourCost/{id}")
	public String tourCost(Principal p, @PathVariable(value = "id")Long id, Model model) {
		User activeUser= activeUser(p);
		TouristPackage tourPackage= touristPackageService.findTourPackage(id);
		touristPackageService.packageCost(tourPackage);
		model.addAttribute("tourist", new TouristDelete());
		model.addAttribute("packages", touristPackageService.listOfUserPackages(activeUser));
		model.addAttribute("tourPackage", tourPackage);
		model.addAttribute("tourist", new TouristDelete());
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/packages";
	}
	
	@RequestMapping("/user/registerTourContents/{id}")
	public String registerTourContents(Principal p, @PathVariable(value = "id")Long id, Model model) {
		User activeUser= activeUser(p);
		TouristPackage tourPackage= touristPackageService.findTourPackage(id);
		model.addAttribute("contents", contentService.findAllContents());
		model.addAttribute("activities", activityService.listOfActivities());
		model.addAttribute("tourPackageId", tourPackage.getId());
		model.addAttribute("tourPackage", tourPackage);
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/activities";
	}
	
	
	@RequestMapping("/user/saveTourContents/{id}")
	public String saveTourContents(@RequestParam(value="packs", required = false) long []packs, @PathVariable(value = "id")Long id, Model model, Principal p) {
		User activeUser= activeUser(p);
		TouristPackage tourPackage= touristPackageService.findTourPackage(id);
		List<ActivityContent> oldContents= (List<ActivityContent>) tourPackage.getContents();
		tourPackage.setContents(contentService.findByListContents(packs, oldContents));
		touristPackageService.saveTourPackage(tourPackage);
		model.addAttribute("contents", contentService.findAllContents());
		model.addAttribute("activities", activityService.listOfActivities());
		model.addAttribute("tourPackage", tourPackage);
		model.addAttribute("names", activeUser.getLastName()+" "+activeUser.getFirstName());
		return "user/activities";
	}
	
	@RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 1;
        Page<Activity> page = activityService.findPaginatedActivities(pageNo, pageSize);
        List<Activity>  activities= page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("activities", activities);
        return "user/activities";
    }

	
}
