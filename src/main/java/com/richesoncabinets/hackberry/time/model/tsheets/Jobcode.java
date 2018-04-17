package com.richesoncabinets.hackberry.time.model.tsheets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Jobcode {
	private long id;
	private long parent_id;
	private boolean assigned_to_all;
	private boolean billable;
	private boolean active;
	private String type;
	private boolean has_children;
	private String short_code;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}
	public boolean isAssigned_to_all() {
		return assigned_to_all;
	}
	public void setAssigned_to_all(boolean assigned_to_all) {
		this.assigned_to_all = assigned_to_all;
	}
	public boolean isBillable() {
		return billable;
	}
	public void setBillable(boolean billable) {
		this.billable = billable;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isHas_children() {
		return has_children;
	}
	public void setHas_children(boolean has_children) {
		this.has_children = has_children;
	}
	public String getShort_code() {
		return short_code;
	}
	public void setShort_code(String short_code) {
		this.short_code = short_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
