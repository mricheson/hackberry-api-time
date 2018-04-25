package com.richesoncabinets.hackberry.time.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.richesoncabinets.hackberry.time.model.tsheets.Timesheet;
import com.richesoncabinets.hackberry.time.model.tsheets.User;

public class UserTimesheetBuilder {
	private static final Comparator<Timesheet> TIMESHEET_COMPARATOR = (time1, time2) -> {
		if (time1 == null && time2 == null)
			return 0;

		if (time1 == null)
			return -1;

		if (time2 == null)
			return 1;

		return time1.getStart().compareTo(time2.getStart());
	};

	private UserTimesheetBuilder() {
		// do nothing
	}

	public static List<UserTimesheet> of(Collection<User> users, Collection<Timesheet> timesheets) {
		Map<Long, List<Timesheet>> userMappedTimesheets = timesheets.stream()
				.collect(Collectors.groupingBy(Timesheet::getUser_id));

		return users.stream().map(user -> UserTimesheetBuilder.of(user, userMappedTimesheets.get(user.getId())))
				.sorted((a, b) -> a.getUser().getLast_name().compareToIgnoreCase(b.getUser().getLast_name()))
				.collect(Collectors.toList());
	}

	public static UserTimesheet of(User user, Collection<Timesheet> timesheets) {
		UserTimesheet userTimesheet = new UserTimesheet();
		userTimesheet.setUser(user);
		if (timesheets != null) {
			userTimesheet.setTimesheets(timesheets.stream().sorted(TIMESHEET_COMPARATOR)
					.collect(Collectors.toList()));
//			userTimesheet.setClockInTime(
//					userTimesheet.getTimesheets().stream().map(Timesheet::getStart).findFirst().orElse(null));
		}
		return userTimesheet;
	}
}
