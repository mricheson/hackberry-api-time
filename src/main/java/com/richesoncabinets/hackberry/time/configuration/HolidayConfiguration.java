package com.richesoncabinets.hackberry.time.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "holiday")
public class HolidayConfiguration {

	private String url;
	private String key;
	private List<String> observed = new ArrayList<>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getObserved() {
		return observed;
	}

	public void setObserved(List<String> observed) {
		this.observed = observed;
	}
}
