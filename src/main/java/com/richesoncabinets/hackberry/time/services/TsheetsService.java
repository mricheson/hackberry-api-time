package com.richesoncabinets.hackberry.time.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.richesoncabinets.hackberry.time.configuration.TsheetsConfiguration;
import com.richesoncabinets.hackberry.time.model.tsheets.TimesheetsResult;
import com.richesoncabinets.hackberry.time.model.tsheets.UsersResult;

@Service
public class TsheetsService {

	@Autowired
	private TsheetsConfiguration tsheetsConfiguration;

	private <T> T executeGet(String endpoint, String query, Class<T> clazz) {
		return new RestTemplate().exchange(buildUrl(endpoint, query), HttpMethod.GET, buildHeaders(), clazz).getBody();
	}

	private String buildUrl(String endpoint, String query) {
		return tsheetsConfiguration.getRootUrl() + endpoint + (StringUtils.isEmpty(query) ? "" : "?" + query);
	}

	private HttpEntity<String> buildHeaders() {
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + tsheetsConfiguration.getToken());

		return new HttpEntity<>("parameters", header);
	}

	public TimesheetsResult getTimesheets(String queryString) {
		queryString = queryString + (queryString.length() == 0 ? "" : "&") + "per_page=50";
		System.out.println(queryString);
		TimesheetsResult cumulativeResult = executeGet("timesheets", queryString, TimesheetsResult.class);
		TimesheetsResult result = cumulativeResult;
		for (int page = 2; result.getResults().getTimesheets().size() == 50; page++) {
			String pagedQuery = queryString + "&page=" + page;
			System.out.println(pagedQuery);
			result = executeGet("timesheets", pagedQuery, TimesheetsResult.class);
			cumulativeResult.merge(result);
		}

		return cumulativeResult;
	}

	public UsersResult getUsers(String queryString) {
		return executeGet("users", queryString, UsersResult.class);
	}
}
