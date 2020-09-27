package com.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.activity.Activity;
import com.domain.activity.ActivityContent;

@Service
public interface ActivityContentService {

	ActivityContent saveActivityContent(ActivityContent activityContent);
	
	ActivityContent findActivityContent(Long id);
	
	List<ActivityContent> findActContents(Activity activity);
	
	List<ActivityContent> findAllContents();
	
	List<ActivityContent> findByListContents(long [] packs, List<ActivityContent> oldContents);
	
}
