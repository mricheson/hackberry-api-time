package com.richesoncabinets.hackberry.time;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richesoncabinets.hackberry.time.model.UserTimesheet;
import com.richesoncabinets.hackberry.time.services.TimesheetService;

@RestController
public class TimeController {

	@Autowired
	private TimesheetService timesheetService; 

	@RequestMapping("/time")
	public List<UserTimesheet> getTimesheets(@RequestParam(value = "date", defaultValue = "") String date) {	
		
		return timesheetService.getUserTimesheetsForDay(LocalDate.parse(date));

	}
}
