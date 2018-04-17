package com.richesoncabinets.hackberry.time.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.richesoncabinets.hackberry.time.model.UserTimesheet;
import com.richesoncabinets.hackberry.time.model.UserTimesheetBuilder;
import com.richesoncabinets.hackberry.time.model.tsheets.TimesheetsResult;
import com.richesoncabinets.hackberry.time.model.tsheets.UsersResult;

@Configuration
public class TimesheetService {

	@Autowired 
	private TsheetsService tsheetsService;


	public List<UserTimesheet> getUserTimesheetsForDay(LocalDate date) {
		String query = String.format("on_the_clock=both&start_date=%1$s&end_date=%1$s&supplemental_data=yes", date.toString());
		TimesheetsResult  timesheets = tsheetsService.getTimesheets(query);
		
		UsersResult users = tsheetsService.getUsers("");
		return UserTimesheetBuilder.of(users.getResults().getUsers().values(), timesheets.getResults().getTimesheets().values());
	}
}
