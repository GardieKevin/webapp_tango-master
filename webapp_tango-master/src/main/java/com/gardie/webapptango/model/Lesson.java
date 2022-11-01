package com.gardie.webapptango.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
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
