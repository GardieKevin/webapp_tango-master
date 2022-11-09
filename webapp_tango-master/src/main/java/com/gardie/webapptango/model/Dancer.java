package com.gardie.webapptango.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.util.List;

@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Dancer {

	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	private String danceLevel;
	
	private String telephone;

	private String email;

	private String gender;

	private List<Lesson> followedLessons;
	
}
