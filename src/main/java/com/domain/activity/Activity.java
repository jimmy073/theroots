package com.domain.activity;

import java.util.List;

import javax.persistence.*;



@Entity
public class Activity {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false)
	private String name;
	private String video;
	@OneToMany(mappedBy = "activity", fetch = FetchType.EAGER)
	private List<ActivityContent> contents;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public List<ActivityContent> getContents() {
		return contents;
	}
	public void setContents(List<ActivityContent> contents) {
		this.contents = contents;
	}
	

}




