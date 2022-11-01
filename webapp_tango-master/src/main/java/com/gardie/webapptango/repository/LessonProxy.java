package com.gardie.webapptango.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gardie.webapptango.CustomProperties;
import com.gardie.webapptango.model.Lesson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LessonProxy {

	@Autowired
	private CustomProperties props;

	/**
	 * Get all lessons
	 * @return An iterable of all lessons
	 */
	public Iterable<Lesson> getLessons() {

		String baseApiUrl = props.getApiUrl();
		String getLessonsUrl = baseApiUrl + "/lessons";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Lesson>> response = restTemplate.exchange(
				getLessonsUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Lesson>>() {}
			);
		
		log.debug("Get Lessons call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get an lesson by the id
	 * @param id The id of the lesson
	 * @return The lesson which matches the id
	 */
	public Lesson getLesson(int id) {
		String baseApiUrl = props.getApiUrl();
		String getLessonUrl = baseApiUrl + "/lesson/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Lesson> response = restTemplate.exchange(
				getLessonUrl, 
				HttpMethod.GET, 
				null,
				Lesson.class
			);
		
		log.debug("Get Lesson call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new lesson 
	 * @param l A new lesson (without an id)
	 * @return The lesson full filled (with an id)
	 */
	public Lesson createLesson(Lesson l) {
		String baseApiUrl = props.getApiUrl();
		String createLessonUrl = baseApiUrl + "/lesson";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Lesson> request = new HttpEntity<Lesson>(l);
		ResponseEntity<Lesson> response = restTemplate.exchange(
				createLessonUrl, 
				HttpMethod.POST, 
				request, 
				Lesson.class);
		
		log.debug("Create Lesson call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update a lesson - using the PUT HTTP Method.
	 * @param l Existing lesson to update
	 */
	public Lesson updateLesson(Lesson l) {
		String baseApiUrl = props.getApiUrl();
		String updateLessonUrl = baseApiUrl + "/lesson/" + l.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Lesson> request = new HttpEntity<Lesson>(l);
		ResponseEntity<Lesson> response = restTemplate.exchange(
				updateLessonUrl, 
				HttpMethod.PUT, 
				request, 
				Lesson.class);
		
		log.debug("Update Lesson call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete a lesson using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param l The lesson to delete
	 */
	public void deleteLesson(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteLessonUrl = baseApiUrl + "/lesson/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteLessonUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Lesson call " + response.getStatusCode().toString());
	}

}

