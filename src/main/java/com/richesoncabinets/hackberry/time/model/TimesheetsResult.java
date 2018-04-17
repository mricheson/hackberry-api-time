package com.richesoncabinets.hackberry.time.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimesheetsResult {
	private Timesheets results;
	private boolean more;
	private SupplementalData supplemental_data;

	public Timesheets getResults() {
		return results;
	}

	public void setResults(Timesheets results) {
		this.results = results;
	}

	public boolean isMore() {
		return more;
	}

	public void setMore(boolean more) {
		this.more = more;
	}

	public SupplementalData getSupplemental_data() {
		return supplemental_data;
	}

	public void setSupplemental_data(SupplementalData supplemental_data) {
		this.supplemental_data = supplemental_data;
	}
}
