package com.richesoncabinets.hackberry.time.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import com.richesoncabinets.hackberry.time.model.tsheets.Jobcode;
import com.richesoncabinets.hackberry.time.model.tsheets.Timesheet;
import com.richesoncabinets.hackberry.time.model.tsheets.User;

public class UserTimesheet {
	private User user;
	private ZonedDateTime clockInTime;
	private List<Timesheet> timesheets;
	private List<Timesheet> exceptions;
	private Map<String,Jobcode> codes;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ZonedDateTime getClockInTime() {
		return clockInTime;
	}
	public void setClockInTime(ZonedDateTime clockInTime) {
		this.clockInTime = clockInTime;
	}
	public List<Timesheet> getTimesheets() {
		return timesheets;
	}
	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
	public List<Timesheet> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Timesheet> exceptions) {
		this.exceptions = exceptions;
	}
	public Map<String,Jobcode> getCodes() {
		return codes;
	}
	public void setCodes(Map<String,Jobcode> codes) {
		this.codes = codes;
	}
}
