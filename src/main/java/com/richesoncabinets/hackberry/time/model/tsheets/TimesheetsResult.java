package com.richesoncabinets.hackberry.time.model.tsheets;

public class TimesheetsResult  extends Result<Timesheets>{
	@Override
	protected void merge(Timesheets otherResults) {
		getResults().getTimesheets().putAll(otherResults.getTimesheets());
	}
}
