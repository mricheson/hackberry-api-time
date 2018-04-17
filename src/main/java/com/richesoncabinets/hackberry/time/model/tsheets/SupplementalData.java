package com.richesoncabinets.hackberry.time.model.tsheets;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplementalData {
	private Map<String, Jobcode> jobcodes;
	private Map<String, User> users;

	public Map<String, Jobcode> getJobcodes() {
		return jobcodes;
	}

	public void setJobcodes(Map<String, Jobcode> jobcodes) {
		this.jobcodes = jobcodes;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public void merge(SupplementalData other) {
		
		this.jobcodes.putAll(other.getJobcodes());
		this.users.putAll(other.getUsers());
	}
}
