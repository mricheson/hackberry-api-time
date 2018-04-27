package com.richesoncabinets.hackberry.time.configuration;

import java.util.Arrays;
import java.util.List;

public class TsheetsCodes {

	private List<String> breakCodes = Arrays.asList("SB", "LB", "B");
	private String callIn = "C";
	private String personalDayApproved = "P";
	private String personalDayUnapproved = "U";
	private String sick = "S";

	public List<String> getBreakCodes() {
		return breakCodes;
	}

	public void setBreakCodes(List<String> breakCodes) {
		this.breakCodes = breakCodes;
	}

	public String getCallIn() {
		return callIn;
	}

	public void setCallIn(String callIn) {
		this.callIn = callIn;
	}

	public String getPersonalDayApproved() {
		return personalDayApproved;
	}

	public void setPersonalDayApproved(String personalDayApproved) {
		this.personalDayApproved = personalDayApproved;
	}

	public String getPersonalDayUnapproved() {
		return personalDayUnapproved;
	}

	public void setPersonalDayUnapproved(String personalDayUnapproved) {
		this.personalDayUnapproved = personalDayUnapproved;
	}

	public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}
}
