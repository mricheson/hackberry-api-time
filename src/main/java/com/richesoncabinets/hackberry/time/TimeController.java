package com.richesoncabinets.hackberry.time;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.richesoncabinets.hackberry.time.configuration.TsheetsConfiguration;
import com.richesoncabinets.hackberry.time.model.tsheets.TimesheetsResult;

@RestController
public class TimeController {

	@Autowired
	private TsheetsConfiguration tsheetsConfiguration;

	@RequestMapping("/time")
	public TimesheetsResult getTimesheets(@RequestParam(value = "date", defaultValue = "") String date) {

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + tsheetsConfiguration.getToken());

		HttpEntity<String> request = new HttpEntity<>("parameters", header);

		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("%stimesheets?start_date=%s&end_date=%s&supplemental_data=yes",
				tsheetsConfiguration.getRootUrl(), date, date);

		System.out.println(url);
		
		ResponseEntity<TimesheetsResult> s = restTemplate.exchange(url, HttpMethod.GET, request,
				TimesheetsResult.class);
		return s.getBody();

	}
}
