package com.richesoncabinets.hackberry.time.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheets {
	private Map<String, Timesheet> timesheets;

	public Map<String, Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(Map<String, Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
}
