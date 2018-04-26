package com.richesoncabinets.hackberry.time.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.richesoncabinets.hackberry.time.model.tsheets.Jobcode;
import com.richesoncabinets.hackberry.time.model.tsheets.Timesheet;
import com.richesoncabinets.hackberry.time.model.tsheets.User;

public class UserTimesheetBuilder {
	private static final String JOB_CODE_TYPE_REGULAR = "regular";
	private static final Comparator<Timesheet> TIMESHEET_COMPARATOR = (time1, time2) -> {
		if (!time1.getStartAsZonedDateTime().isPresent() && !time2.getStartAsZonedDateTime().isPresent())
			return 0;

		if (!time1.getStartAsZonedDateTime().isPresent())
			return 1;

		if (!time2.getStartAsZonedDateTime().isPresent())
			return -1;

		return time1.getStartAsZonedDateTime().get().compareTo(time2.getStartAsZonedDateTime().get());
	};

	private UserTimesheetBuilder() {
		// do nothing
	}

	public static List<UserTimesheet> of(Collection<User> users, Collection<Timesheet> timesheets,
			Map<String, Jobcode> jobCodes) {
		Map<Long, List<Timesheet>> userMappedTimesheets = timesheets.stream()
				.collect(Collectors.groupingBy(Timesheet::getUser_id));

		return users.stream()
				.map(user -> UserTimesheetBuilder.of(user, userMappedTimesheets.get(user.getId()), jobCodes))
				.sorted((a, b) -> a.getUser().getLast_name().compareToIgnoreCase(b.getUser().getLast_name()))
				.collect(Collectors.toList());
	}

	public static UserTimesheet of(User user, Collection<Timesheet> timesheets, Map<String, Jobcode> jobCodes) {
		UserTimesheet userTimesheet = new UserTimesheet();
		userTimesheet.setUser(user);

		Predicate<Timesheet> timesheetJobCodeClassifier = t -> {
			Jobcode jobcode = jobCodes.get(Long.toString(t.getJobcode_id()));
			if (jobcode != null && jobcode.getType().equals(JOB_CODE_TYPE_REGULAR))
				return false;

			return true;
		};

		if (timesheets != null) {
			Map<Boolean, List<Timesheet>> timesheetsPartitionedByExceptionJobCode = timesheets.stream()
					.collect(Collectors.partitioningBy(timesheetJobCodeClassifier));

			List<Timesheet> punches = timesheetsPartitionedByExceptionJobCode.get(false);
			if (punches != null) {
				punches.sort(TIMESHEET_COMPARATOR);
				userTimesheet.setTimesheets(punches);
				userTimesheet.setClockInTime(punches.stream().map(Timesheet::getStartAsZonedDateTime)
						.filter(Optional::isPresent).map(Optional::get).findFirst().orElse(null));
			}
			
			userTimesheet.setExceptions(timesheetsPartitionedByExceptionJobCode.get(true));
		}

		return userTimesheet;
	}
}
