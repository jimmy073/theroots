package com.service.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.Activity;
import com.domain.activity.ActivityContent;
import com.repository.activity.ActivityContentReposirtory;

@Service
public class ActivityContentServiceImpl implements ActivityContentService{
	
	private ActivityContentReposirtory activityContentRepository;
	
	@Autowired
	public void setActivityContentReposirtory(ActivityContentReposirtory activityContentReposirtory) {
		activityContentRepository = activityContentReposirtory;
	}

	@Override
	public ActivityContent saveActivityContent(ActivityContent activityContent) {
		return activityContentRepository.save(activityContent);
	}

	@Override
	public ActivityContent findActivityContent(Long id) {
		Optional<ActivityContent> activityContent = this.activityContentRepository.findById(id);
        if (activityContent.isPresent())
            return activityContent.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<ActivityContent> findActContents(Activity activity) {
		return activityContentRepository.findActivityContentByActivity(activity);
	}

	@Override
	public List<ActivityContent> findAllContents() {
		return activityContentRepository.findAll();
	}


	@Override
	public List<ActivityContent> findByListContents(long[] packs, List<ActivityContent> oldContents) {
		List<ActivityContent> contents = new ArrayList<>();
		if(packs!=null) {
			ActivityContent content=null;
			for(int i=0; i<packs.length; i++) {
				content = findActivityContent(packs[i]);
				contents.add(content);
				if(oldContents.size()>0) {
					contents.addAll(oldContents);
				}
					
			}
			return contents;
		}
		
		return null;
	}

}
