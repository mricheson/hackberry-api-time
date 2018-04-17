package com.richesoncabinets.hackberry.time.model.tsheets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResult extends Result<Users> {

	@Override
	protected void merge(Users otherResults) {
		getResults().getUsers().putAll(otherResults.getUsers());
	}
}
