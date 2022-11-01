package com.gardie.webapptango.model;

import lombok.Data;

import java.util.List;

@Data
public class Dancer {

	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	private String danceLevel;
	
	private String telephone;

	private String email;

	private List<Lesson> followedLessons;
	
}
