package com.repository.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.Activity;
import com.domain.activity.ActivityContent;

@Repository
public interface ActivityContentReposirtory extends JpaRepository<ActivityContent, Long>{

	List<ActivityContent> findActivityContentByActivity(Activity activity);
}
