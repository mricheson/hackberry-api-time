package com.richesoncabinets.hackberry.time.services;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.richesoncabinets.hackberry.time.configuration.TsheetsConfiguration;
import com.richesoncabinets.hackberry.time.model.tsheets.Result;
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

	private <R, T extends Result<R>> T getResultsFromAllPages(String endpoint, String queryString, Class<T> resultClass,
			Function<R, Integer> pageSize) {
		queryString = queryString + (queryString.length() == 0 ? "" : "&") + "per_page=50";

		T cumulativeResult = executeGet(endpoint, queryString, resultClass);
		T result = cumulativeResult;

		try {
			for (int page = 2; pageSize.apply(result.getResults()) == 50; page++) {
				String pagedQuery = queryString + "&page=" + page;
				result = executeGet(endpoint, pagedQuery, resultClass);
				cumulativeResult.merge(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to get next page of results.  This might be because there are exactly 50 results.");
		}

		return cumulativeResult;
	}

	public TimesheetsResult getTimesheets(String queryString) {
		return getResultsFromAllPages("timesheets", queryString, TimesheetsResult.class, u -> u.getTimesheets().size());
	}

	public UsersResult getUsers(String queryString) {
		return getResultsFromAllPages("users", queryString, UsersResult.class, u -> u.getUsers().size());
	}
}
