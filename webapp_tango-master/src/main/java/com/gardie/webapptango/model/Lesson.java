package com.gardie.webapptango.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Lesson {

	private Integer id;
	
	private String lessonName;
	
	private String description;
	
	private Date lessonDate;
	
	private String lessonTime;
	
	private String danceLevel;
	
	private Integer price;

	private List<Dancer> attendees;
	
}
