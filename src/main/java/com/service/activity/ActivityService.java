package com.service.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.domain.activity.Activity;

@Service
public interface ActivityService {

	Activity saveActivity(Activity activity);
	
	Activity findActivity(Long id);
	
	List<Activity> listOfActivities();
	
	Page<Activity> findPaginatedActivities(int pageNo, int pageSize);
	
	
}
