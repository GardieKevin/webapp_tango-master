package com.gardie.webapptango;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.gardie.webapptango")
public class CustomProperties {

	private String apiUrl;
	
}
