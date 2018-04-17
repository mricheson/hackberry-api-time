package com.richesoncabinets.hackberry.time.model.tsheets;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {
	private long id;
	private long user_id;
	private long jobcode_id;
	private ZonedDateTime start;
	private ZonedDateTime end;
	private long duration;
	private LocalDate date;
	private String tz_str;
	private String type;
	private String location;
	private boolean on_the_clock;
	private int locked;
	private String notes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getJobcode_id() {
		return jobcode_id;
	}
	public void setJobcode_id(long jobcode_id) {
		this.jobcode_id = jobcode_id;
	}
	public ZonedDateTime getStart() {
		return start;
	}
	public void setStart(ZonedDateTime start) {
		this.start = start;
	}
	public ZonedDateTime getEnd() {
		return end;
	}
	public void setEnd(ZonedDateTime end) {
		this.end = end;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTz_str() {
		return tz_str;
	}
	public void setTz_str(String tz_str) {
		this.tz_str = tz_str;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isOn_the_clock() {
		return on_the_clock;
	}
	public void setOn_the_clock(boolean on_the_clock) {
		this.on_the_clock = on_the_clock;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public ZonedDateTime getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(ZonedDateTime last_modified) {
		this.last_modified = last_modified;
	}
	private ZonedDateTime last_modified;
}
