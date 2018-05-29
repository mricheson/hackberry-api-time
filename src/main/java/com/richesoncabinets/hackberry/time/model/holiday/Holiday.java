package com.richesoncabinets.hackberry.time.model.holiday;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {
	private String name;
	private LocalDate date;
	private LocalDate observed;
	@JsonProperty("public")
	private boolean publicHoliday;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getObserved() {
		return observed;
	}
	public void setObserved(LocalDate observed) {
		this.observed = observed;
	}
	public boolean isPublicHoliday() {
		return publicHoliday;
	}
	public void setPublicHoliday(boolean publicHoliday) {
		this.publicHoliday = publicHoliday;
	}
	
}
