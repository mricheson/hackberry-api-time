package com.richesoncabinets.hackberry.time;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TimeController {

	private static final String TSHEETS_TOKEN = "S.4__5bef0f3becb66d52b38539bea60456fa8a76b06a";
	private static final String URL = "https://rest.tsheets.com/api/v1/timesheets?start_date=%s&end_date=%s&supplemental_data=yes";

	@RequestMapping("/test")
	public String test() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + TSHEETS_TOKEN);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = new RestTemplate().exchange("https://rest.tsheets.com/api/v1/timesheets?start_date=%s&end_date=%s&supplemental_data=yes", HttpMethod.GET, request, String.class);
		Object account = response.getBody();
		
		return "success";
	}

	@RequestMapping("/time")
	public String getTimesheets(@RequestParam(value = "date", defaultValue = "") String date) {

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization",
				Base64.getEncoder().encodeToString(String.format("Bearer %s", TSHEETS_TOKEN).getBytes()));

		HttpEntity<String> request = new HttpEntity<>("parameters", header);

		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(
				"https://rest.tsheets.com/api/v1/timesheets?start_date=%s&end_date=%s&supplemental_data=yes", date,
				date);

		System.out.println(url);

		ResponseEntity<String> s = restTemplate.exchange("https://rest.tsheets.com/api/v1/timesheets?start_date=2018-04-13&end_date=2018-04-13&supplemental_data=yes", HttpMethod.GET, request, String.class);
		return s.getBody();

	}
}
