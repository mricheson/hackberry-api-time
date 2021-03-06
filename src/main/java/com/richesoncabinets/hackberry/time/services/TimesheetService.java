package com.richesoncabinets.hackberry.time.services;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.richesoncabinets.hackberry.time.model.DailyAttendanceReport;
import com.richesoncabinets.hackberry.time.model.UserTimesheetBuilder;
import com.richesoncabinets.hackberry.time.model.holiday.Holiday;
import com.richesoncabinets.hackberry.time.model.tsheets.TimesheetsResult;
import com.richesoncabinets.hackberry.time.model.tsheets.UsersResult;

@Configuration
public class TimesheetService {

	@Autowired
	private TsheetsService tsheetsService;
	
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private UserTimesheetBuilder userTimesheetBuilder;

	public DailyAttendanceReport getUserTimesheetsForDay(LocalDate date) {
		String query = String.format("on_the_clock=both&start_date=%1$s&end_date=%1$s&supplemental_data=yes",
				date.toString());

		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		
		try {
			Future<TimesheetsResult> timesheetsFuture = threadExecutor
					.submit(() -> tsheetsService.getTimesheets(query));
			Future<UsersResult> usersFuture = threadExecutor.submit(() -> tsheetsService.getUsers(""));
			Future<Map<LocalDate,Holiday>> holidaysFuture = threadExecutor.submit(() -> holidayService.getHolidaysForMonth(date));

			TimesheetsResult timesheets = timesheetsFuture.get();
			UsersResult users = usersFuture.get();
			Map<LocalDate,Holiday> holidays = holidaysFuture.get();
			
			users.getResults().getUsers().putAll(timesheets.getSupplemental_data().getUsers());

			return DailyAttendanceReport.of(userTimesheetBuilder.of(date, users.getResults().getUsers().values(),
					timesheets.getResults().getTimesheets().values(), timesheets.getSupplemental_data().getJobcodes(), holidays),
					timesheets.getSupplemental_data().getJobcodes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
