package com.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.domain.activity.Activity;
import com.repository.activity.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	
	@Autowired	
	public void setActivityRepository(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	

	@Override
	public Activity findActivity(Long id) {
		Optional<Activity> activity = this.activityRepository.findById(id);
        if (activity.isPresent())
            return activity.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<Activity> listOfActivities() {
		return this.activityRepository.findAll();
	}

	@Override
	public Page<Activity> findPaginatedActivities(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.activityRepository.findAll(pageable);
	}

	

}
