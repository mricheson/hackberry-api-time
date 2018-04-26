package com.richesoncabinets.hackberry.time.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix ="tsheets")
public class TsheetsConfiguration {
	private String token;
	private String rootUrl = "https://rest.tsheets.com/api/v1/";
	private List<String> breakCodes = Arrays.asList("SB", "LB", "B"); 
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public List<String> getBreakCodes() {
		return breakCodes;
	}
	public void setBreakCodes(List<String> breakCodes) {
		this.breakCodes = breakCodes;
	}
}
