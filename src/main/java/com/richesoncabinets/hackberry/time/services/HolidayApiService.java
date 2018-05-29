package com.richesoncabinets.hackberry.time.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.richesoncabinets.hackberry.time.configuration.HolidayConfiguration;
import com.richesoncabinets.hackberry.time.model.holiday.HolidayResult;

@Service
public class HolidayApiService {

	@Autowired
	private HolidayConfiguration configuration;

	private String buildUrl(int year, int month) {
		return String.format("%s?key=%s&public=true&country=US&year=%s&month=%s", configuration.getUrl(),
				configuration.getKey(), year, month);
	}

	private String buildUrl(int year, int month, int day) {
		return String.format("%s&day=%s", buildUrl(year, month), day);
	}

	public HolidayResult getHolidaysForMonth(int year, int month) {
		return executeGet(buildUrl(year, month));
	}

	public HolidayResult getHolidaysForMonthBeforeGivenDay(int year, int month, int day) {
		return executeGet(buildUrl(year, month, day));
	}

	private HolidayResult executeGet(String url) {
		return new RestTemplate().exchange(url, HttpMethod.GET, null, HolidayResult.class).getBody();
	}
}
