package com.richesoncabinets.hackberry.time.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.richesoncabinets.hackberry.time.model.tsheets.Timesheet;
import com.richesoncabinets.hackberry.time.model.tsheets.User;

public class UserTimesheetBuilder {
	private UserTimesheetBuilder() {
		// do nothing
	}

	public static List<UserTimesheet> of(Collection<User> users, Collection<Timesheet> timesheets) {
		Map<Long, List<Timesheet>> userMappedTimesheets = timesheets.stream()
				.collect(Collectors.groupingBy(Timesheet::getUser_id));
		
		userMappedTimesheets.forEach((k,v) -> System.out.println(k + " : "+ v.size()));

		return users.stream().map(user -> UserTimesheetBuilder.of(user, userMappedTimesheets.get(user.getId())))
				.collect(Collectors.toList());
	}

	public static UserTimesheet of(User user, Collection<Timesheet> timesheets) {
		UserTimesheet userTimesheet = new UserTimesheet();
		userTimesheet.setUser(user);
		userTimesheet.setTimesheets(timesheets.stream().sorted((t1, t2) -> t1.getStart().compareTo(t2.getStart()))
				.collect(Collectors.toList()));
		userTimesheet.setClockInTime(userTimesheet.getTimesheets().stream().map(Timesheet::getStart).findFirst().orElse(null));
		return userTimesheet;
	}
}
