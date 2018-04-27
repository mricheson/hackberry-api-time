package com.richesoncabinets.hackberry.time.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tsheets")
public class TsheetsConfiguration {
	private String token;
	private String rootUrl = "https://rest.tsheets.com/api/v1/";
	private TsheetsCodes codes;

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

	public TsheetsCodes getCodes() {
		return codes;
	}

	public void setCodes(TsheetsCodes codes) {
		this.codes = codes;
	}
}
