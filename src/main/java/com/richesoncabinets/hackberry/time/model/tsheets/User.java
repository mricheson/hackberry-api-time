package com.richesoncabinets.hackberry.time.model.tsheets;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class User {
	private long id;
	private String first_name;
	private String last_name;
	private long group_id;
	private boolean active;
	private long employee_number;
	private boolean salaried;
	private boolean exempt;
	private String usernam;
	private String email;
	private boolean email_verified;
	private String payroll_id;
	private LocalDate hire_date;
//	private LocalDate term_date;
	private ZonedDateTime last_modified;
	private ZonedDateTime last_active;
	private ZonedDateTime created;
	private String client_url;
	private String company_name;
	private String profile_image_url;
	private String mobile_number;
	private LocalDate submitted_to;
	private LocalDate approved_to;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(long employee_number) {
		this.employee_number = employee_number;
	}
	public boolean isSalaried() {
		return salaried;
	}
	public void setSalaried(boolean salaried) {
		this.salaried = salaried;
	}
	public boolean isExempt() {
		return exempt;
	}
	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}
	public String getUsernam() {
		return usernam;
	}
	public void setUsernam(String usernam) {
		this.usernam = usernam;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}
	public String getPayroll_id() {
		return payroll_id;
	}
	public void setPayroll_id(String payroll_id) {
		this.payroll_id = payroll_id;
	}
	public LocalDate getHire_date() {
		return hire_date;
	}
	public void setHire_date(LocalDate hire_date) {
		this.hire_date = hire_date;
	}
//	public LocalDate getTerm_date() {
//		return term_date;
//	}
//	public void setTerm_date(LocalDate term_date) {
//		this.term_date = term_date;
//	}
	public ZonedDateTime getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(ZonedDateTime last_modified) {
		this.last_modified = last_modified;
	}
	public ZonedDateTime getLast_active() {
		return last_active;
	}
	public void setLast_active(ZonedDateTime last_active) {
		this.last_active = last_active;
	}
	public ZonedDateTime getCreated() {
		return created;
	}
	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}
	public String getClient_url() {
		return client_url;
	}
	public void setClient_url(String client_url) {
		this.client_url = client_url;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getProfile_image_url() {
		return profile_image_url;
	}
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public LocalDate getSubmitted_to() {
		return submitted_to;
	}
	public void setSubmitted_to(LocalDate submitted_to) {
		this.submitted_to = submitted_to;
	}
	public LocalDate getApproved_to() {
		return approved_to;
	}
	public void setApproved_to(LocalDate approved_to) {
		this.approved_to = approved_to;
	}
}
