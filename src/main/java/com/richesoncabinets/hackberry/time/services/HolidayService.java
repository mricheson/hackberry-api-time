package com.richesoncabinets.hackberry.time.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richesoncabinets.hackberry.time.configuration.HolidayConfiguration;
import com.richesoncabinets.hackberry.time.model.holiday.Holiday;
import com.richesoncabinets.hackberry.time.model.holiday.HolidayResult;

@Service
public class HolidayService {

	@Autowired
	private HolidayApiService holidayApiService;
	
	@Autowired
	private HolidayConfiguration holidayConfiguration;

	public Map<LocalDate, Holiday> getHolidaysForMonth(LocalDate date) {
		LocalDate today = LocalDate.now();
		int differenceFromCurrentMonth = Period.between(date, today).getMonths();
		
		HolidayResult holidayResult = null; 
		if (differenceFromCurrentMonth <= 0) {
			LocalDate yesterday = today.minusDays(1);
			holidayResult = holidayApiService.getHolidaysForMonthBeforeGivenDay(yesterday.getYear(), yesterday.getMonthValue(),
					yesterday.getDayOfMonth());
		} else {
			holidayResult = holidayApiService.getHolidaysForMonth(date.getYear(), date.getMonthValue());
		}

		System.out.println(holidayConfiguration.getObserved());
		return holidayResult.getHolidays().stream().filter(h -> holidayConfiguration.getObserved().contains(h.getName())).collect(Collectors.toMap(Holiday::getObserved, Function.identity()));
	}

}
