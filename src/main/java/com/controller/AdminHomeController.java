package com.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.domain.activity.Activity;
import com.domain.activity.ActivityContent;
import com.domain.activity.Home;
import com.domain.activity.HomeRoom;
import com.domain.activity.Tent;
import com.domain.finance.Discount;
import com.domain.people.Partner;
import com.service.activity.ActivityContentService;
import com.service.activity.ActivityService;
import com.service.activity.HomeRoomService;
import com.service.activity.HomeService;
import com.service.activity.TentService;
import com.service.finance.DiscountService;
import com.service.people.PartnerService;

import javassist.expr.NewArray;

@Controller
public class AdminHomeController {
	
	private ActivityService activityService;
	private ActivityContentService activityContentService;
	private PartnerService partnerService;
	private HomeService homeService;
	private TentService tentService;
	private HomeRoomService homeRoomService;
	private DiscountService discountService;
	
	//DEPENDENCY INJECTION SETTERS
	
	
	@Autowired	
	public void setHomeRoomService(HomeRoomService homeRoomService) {
		this.homeRoomService = homeRoomService;
	}

	@Autowired
	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}

	@Autowired
	public void setTentService(TentService tentService) {
		this.tentService = tentService;
	}

	@Autowired
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	@Autowired
	public void setActivityContentService(ActivityContentService activityContentService) {
		this.activityContentService = activityContentService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@Autowired
	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

	
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	
	//MANAGING REGISTRATION
	
	@RequestMapping("/admin/registerActivity")
	public String registerActivity(Model model) {
		model.addAttribute("activity", new Activity());
		model.addAttribute("discount", new Discount());
		model.addAttribute("activities", activityService.listOfActivities());
		return "admin/activity";
	}
	
	
	@RequestMapping("/admin/saveActivity")
	public String saveActivity(Activity activity, @RequestParam(name="file") MultipartFile file, Model model) throws Exception {
		String path=uploadActivity(file, activity.getName());
		activity.setVideo(path+activity.getName()+".mp4");
		activityService.saveActivity(activity);
		model.addAttribute("activity", new Activity());
		model.addAttribute("activities", activityService.listOfActivities());
		model.addAttribute("discount", new Discount());
		return "admin/activity";
	}
	
	@RequestMapping("/admin/saveDiscount")
	public String saveDiscount(Discount discount, Model model) {
		if((discount.getPercentage()<=100) && (discount.getPercentage()>=0)) {
			discountService.saveDiscount(discount);
			model.addAttribute("activity", new Activity());
			model.addAttribute("activities", activityService.listOfActivities());
			model.addAttribute("discount", new Discount());
			return "admin/activity";
		}else {
			model.addAttribute("activity", new Activity());
			model.addAttribute("activities", activityService.listOfActivities());
			model.addAttribute("discount", new Discount());
			return "admin/activity";
		}
		
	}
	
	@RequestMapping("/admin/saveActivityContent/{id}")
	public String saveActivityContent(ActivityContent activityContent, @RequestParam(name="foto1") MultipartFile foto1,
			@RequestParam(name="foto2") MultipartFile foto2, Model model,
			@PathVariable(value="id") long id) throws Exception {
		String path = uploadActivityContent(foto1, foto2, activityContent.getName());
		activityContent.setPhotoOne(path+"1.jpg");
		activityContent.setPhotoTwo(path+"2.jpg");
		Activity activity= activityService.findActivity(id);
		activityContent.setActivity(activity);
		activityContentService.saveActivityContent(activityContent);
		List<ActivityContent> contents = this.activityContentService.findActContents(activity);
		model.addAttribute("activity", activity);
		model.addAttribute("foto1", foto1);
		model.addAttribute("foto2", foto2);
		model.addAttribute("content", new ActivityContent());
		model.addAttribute("contents", contents);
		model.addAttribute("activities", activityService.listOfActivities());
		return "admin/activityContent";
	}
		

	@RequestMapping("/admin/activityContents/{id}")
	public String activityContents(Model model, @PathVariable(value="id") long id){
		Activity activity = activityService.findActivity(id);
		List<ActivityContent> contents = this.activityContentService.findActContents(activity);
		model.addAttribute("activity", activity);
		model.addAttribute("contents", contents);
		model.addAttribute("content", new ActivityContent());
		return "admin/activityContent";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin/registerPartner")
	public String showRegisterPartner(Model model) {
		model.addAttribute("partner", new Partner());
		model.addAttribute("partners", partnerService.listOfPartners());
		return "admin/partner";
	}
	
	@RequestMapping("/admin/savePartner")
	public String savePartner(Partner partner, Model model) {
		partnerService.savePartner(partner);
		model.addAttribute("partner", new Partner());
		model.addAttribute("partners", partnerService.listOfPartners());
		return "admin/partner";
	}
		
	@RequestMapping("/admin/registerHome")
	public String showRegisterHome(Model model) {
		model.addAttribute("home", new Home());
		model.addAttribute("homes", homeService.listOfHomes());
		return "admin/home";
	}
	
	@RequestMapping("/admin/saveHome")
	public String saveHome(Home home, Model model, @RequestParam(name="foto1") MultipartFile foto1,
			@RequestParam(name="foto2") MultipartFile foto2, @RequestParam(name="foto3") MultipartFile foto3,
			@RequestParam(name="foto4") MultipartFile foto4) throws Exception {
		String path = uploadHome(foto1, foto2, foto3, foto4, home.getName());
		home.setPhoto1(home.getName()+"1.jpg");
		home.setPhoto2(home.getName()+"2.jpg");
		home.setPhoto3(home.getName()+"3.jpg");
		home.setPhoto4(home.getName()+"4.jpg");
		homeService.saveHome(home);
		model.addAttribute("home", new Home());
		model.addAttribute("homes", homeService.listOfHomes());
		return "admin/home";
	}
	
	@RequestMapping("/admin/registerTent")
	public String showRegisterTent(Model model) {
		model.addAttribute("tent", new Tent());
		model.addAttribute("tents", tentService.listOfTents());
		return "admin/tent";
	}
	
	@RequestMapping("/admin/saveTent")
	public String saveTent(Tent tent, Model model) {
		tentService.saveTent(tent);
		model.addAttribute("tent", new Tent());
		model.addAttribute("tents", tentService.listOfTents());
		return "admin/tent";
	}
	
	@RequestMapping("/admin/registerHomeRoom/{id}")
	public String showRegisterHomeRoom(Model model, @PathVariable(value="id") long id) {
		Home home= homeService.findHome(id);
		model.addAttribute("home", home);
		model.addAttribute("homeRoom", new HomeRoom());
		model.addAttribute("homeRooms", homeRoomService.listOfHomeRooms());
		return "admin/homeRoom";
	}
	
	@RequestMapping("/admin/saveHomeRoom/{id}")
	public String saveHomeRoom(HomeRoom homeRoom, Model model, @PathVariable(value="id") long id) {
		Home home= homeService.findHome(id);
		homeRoom.setHome(home);
		homeRoomService.saveHomeRoom(homeRoom);
		model.addAttribute("homeRoom", new HomeRoom());
		model.addAttribute("home", home);
		model.addAttribute("homeRooms", homeRoomService.listOfHomeRooms());
		return "admin/homeRoom";
	}
	
	@RequestMapping("/admin/homeRooms/{id}")
	public String showHomeRooms(Model model, @PathVariable(value="id") long id) {
		Home home = homeService.findHome(id);
		model.addAttribute("home", home);
		model.addAttribute("homeRoom", new HomeRoom());
		model.addAttribute("homeRooms", homeRoomService.listOfHomeRooms());
		return "admin/homeRoom";
	}
	
	@RequestMapping("/admin/homeFoto/{id}")
	public String showHomefoto(Model model, @PathVariable(value="id") long id) {
		Home home = homeService.findHome(id);
		model.addAttribute("home", home);
		return "admin/ifoto";
	}
	
	
	
	//UPLOADING FUNCTIONS
	
	public String uploadActivity(MultipartFile actVideo, String name) throws Exception {
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		String folder = absolutePath+"/src/main/resources/static/img/activity/";
		byte [] bytes = actVideo.getBytes();
		Path path = Paths.get(folder+name+".mp4");
		Files.write(path, bytes);
		return folder;
	}
	
	public String uploadHome(MultipartFile photo1, MultipartFile photo2,MultipartFile photo3, MultipartFile photo4,
			String name) throws Exception {
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		String folder = absolutePath+"/src/main/resources/static/img/home/";
		byte [] bytes1 = photo1.getBytes();
		byte [] bytes2 = photo2.getBytes();
		byte [] bytes3 = photo3.getBytes();
		byte [] bytes4 = photo4.getBytes();
		Path path1 = Paths.get(folder+name+"1.jpg");
		Path path2 = Paths.get(folder+name+"2.jpg");
		Path path3 = Paths.get(folder+name+"3.jpg");
		Path path4 = Paths.get(folder+name+"4.jpg");
		Files.write(path1, bytes1);
		Files.write(path2, bytes2);
		Files.write(path3, bytes3);
		Files.write(path4, bytes4);
		return folder;
	}
	
	public String uploadActivityContent(MultipartFile photo1, MultipartFile photo2, String name) throws Exception {
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		String folder = absolutePath+"/src/main/resources/static/img/contents/";
		byte [] bytes1 = photo1.getBytes();
		byte [] bytes2 = photo2.getBytes();
		Path path1 = Paths.get(folder+name+"1.jpg");
		Path path2 = Paths.get(folder+name+"2.jpg");
		Files.write(path1, bytes1);
		Files.write(path2, bytes2);
		return folder;
	}
	
}
