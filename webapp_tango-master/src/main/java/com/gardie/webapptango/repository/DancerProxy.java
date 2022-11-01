package com.gardie.webapptango.repository;

import com.gardie.webapptango.CustomProperties;
import com.gardie.webapptango.model.Dancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class DancerProxy {

	@Autowired
	private CustomProperties props;

	/**
	 * Get all dancers
	 * @return An iterable of all dancers
	 */
	public Iterable<Dancer> getDancers() {

		String baseApiUrl = props.getApiUrl();
		String getDancersUrl = baseApiUrl + "/dancers";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Dancer>> response = restTemplate.exchange(
				getDancersUrl,
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Dancer>>() {}
			);
		
		log.debug("Get Dancers call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get a dancer by the id
	 * @param id The id of the dancer
	 * @return The dancer which matches the id
	 */
	public Dancer getDancer(int id) {
		String baseApiUrl = props.getApiUrl();
		String getDancerUrl = baseApiUrl + "/dancer/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Dancer> response = restTemplate.exchange(
				getDancerUrl,
				HttpMethod.GET, 
				null,
				Dancer.class
			);
		
		log.debug("Get Dancer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new dancer
	 * @param d A new dancer (without an id)
	 * @return The dancer full filled (with an id)
	 */
	public Dancer createDancer(Dancer d) {
		String baseApiUrl = props.getApiUrl();
		String createDancerUrl = baseApiUrl + "/dancer";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Dancer> request = new HttpEntity<Dancer>(d);
		ResponseEntity<Dancer> response = restTemplate.exchange(
				createDancerUrl,
				HttpMethod.POST, 
				request, 
				Dancer.class);
		
		log.debug("Create Dancer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update a dancer - using the PUT HTTP Method.
	 * @param d Existing dancer to update
	 */
	public Dancer updateDancer(Dancer d) {
		String baseApiUrl = props.getApiUrl();
		String updateDancerUrl = baseApiUrl + "/dancer/" + d.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Dancer> request = new HttpEntity<Dancer>(d);
		ResponseEntity<Dancer> response = restTemplate.exchange(
				updateDancerUrl,
				HttpMethod.PUT, 
				request, 
				Dancer.class);
		
		log.debug("Update Dancer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete a dancer using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param d The dancer to delete
	 */
	public void deleteDancer(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteDancerUrl = baseApiUrl + "/dancer/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteDancerUrl,
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Dancer call " + response.getStatusCode().toString());
	}

}

