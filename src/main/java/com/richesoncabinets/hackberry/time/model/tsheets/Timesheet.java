package com.richesoncabinets.hackberry.time.model.tsheets;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {
	private long id;
	private long user_id;
	private long jobcode_id;
	private String start;
	private String end;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Optional<ZonedDateTime> getStartAsZonedDateTime() {
		if (StringUtils.isEmpty(getStart()))
			return Optional.empty();
		else
			return Optional.of(ZonedDateTime.parse(getStart()));
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Optional<ZonedDateTime> getEndAsZonedDateTime() {
		if (StringUtils.isEmpty(getEnd()))
			return Optional.empty();
		else
			return Optional.of(ZonedDateTime.parse(getEnd()));
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
