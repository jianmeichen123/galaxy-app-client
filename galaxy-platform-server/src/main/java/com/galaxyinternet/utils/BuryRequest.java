package com.galaxyinternet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuryRequest {
	private static final Logger logger = LoggerFactory.getLogger(BuryRequest.class);
	private String burySaveUrl;
	
	public String getBurySaveUrl() {
		return burySaveUrl;
	}
	public void setBurySaveUrl(String burySaveUrl) {
		this.burySaveUrl = burySaveUrl;
	}
	
	public String burySave(String url){
		return burySaveUrl+url;
	}
	
	
	
	

}
