package com.richesoncabinets.hackberry.time.model;

import java.util.List;
import java.util.Map;

import com.richesoncabinets.hackberry.time.model.tsheets.Jobcode;

public class DailyAttendanceReport {
	private Map<String, Jobcode> jobcodes;
	private List<UserTimesheet> timesheets;

	public Map<String, Jobcode> getJobcodes() {
		return jobcodes;
	}

	public void setJobcodes(Map<String, Jobcode> jobcodes) {
		this.jobcodes = jobcodes;
	}

	public List<UserTimesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<UserTimesheet> timesheets) {
		this.timesheets = timesheets;
	}
	
	public static DailyAttendanceReport of(List<UserTimesheet> timesheets, Map<String, Jobcode> jobcodes)
	{
		DailyAttendanceReport report = new DailyAttendanceReport();
		report.setJobcodes(jobcodes);
		report.setTimesheets(timesheets);
		return report;
	}

}
