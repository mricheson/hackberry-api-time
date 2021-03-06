package com.richesoncabinets.hackberry.time.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richesoncabinets.hackberry.time.configuration.TsheetsConfiguration;
import com.richesoncabinets.hackberry.time.model.holiday.Holiday;
import com.richesoncabinets.hackberry.time.model.tsheets.Jobcode;
import com.richesoncabinets.hackberry.time.model.tsheets.Timesheet;
import com.richesoncabinets.hackberry.time.model.tsheets.User;

@Service
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

	@Autowired
	private TsheetsConfiguration tsheetsConfiguration;

	public List<UserTimesheet> of(LocalDate date, Collection<User> users, Collection<Timesheet> timesheets,
			Map<String, Jobcode> jobCodes, Map<LocalDate,Holiday> holidays) {
		Map<Long, List<Timesheet>> userMappedTimesheets = timesheets.stream()
				.collect(Collectors.groupingBy(Timesheet::getUser_id));

		return users.stream().map(user -> of(date, user, userMappedTimesheets.get(user.getId()), jobCodes, holidays))
				.sorted((a, b) -> a.getUser().getLast_name().compareToIgnoreCase(b.getUser().getLast_name()))
				.collect(Collectors.toList());
	}

	public UserTimesheet of(LocalDate date, User user, Collection<Timesheet> timesheets, Map<String, Jobcode> jobCodes, Map<LocalDate,Holiday> holidays) {
		UserTimesheet userTimesheet = new UserTimesheet();
		userTimesheet.setUser(user);

		Predicate<Timesheet> timesheetJobCodeClassifier = t -> {
			if (t.getJobcode_id() == 0)
				return false;

			Jobcode jobcode = jobCodes.get(Long.toString(t.getJobcode_id()));
			if (jobcode != null && (jobcode.getType().equals(JOB_CODE_TYPE_REGULAR)
					|| tsheetsConfiguration.getCodes().getBreakCodes().contains(jobcode.getShort_code())))
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

		userTimesheet.setAttendanceCodes(evaluateAttendance(date, userTimesheet,jobCodes, holidays));

		return userTimesheet;
	}

	public List<AttendanceCode> evaluateAttendance(LocalDate date, UserTimesheet timesheet, Map<String,Jobcode> jobcodes, Map<LocalDate,Holiday> holidays) {
		List<AttendanceCode> codes = new ArrayList<>();

		if (timesheet.getClockInTime() != null) {
			if (timesheet.getClockInTime().toLocalTime().isBefore(LocalTime.of(5, 0, 0, 0))) {
				codes.add(AttendanceCode.EARLY);
			} else if (timesheet.getClockInTime().toLocalTime().isAfter(LocalTime.of(7, 30, 0, 0))) {
				codes.add(AttendanceCode.LATE);
			}
		}
		else if (timesheet.getClockInTime() == null && holidays.containsKey(date))
		{
			codes.add(AttendanceCode.UNPAID_HOLIDAY);
		}
		else
		{
			codes.add(AttendanceCode.ABSENT);
		}

		if (timesheet.getExceptions() != null) {
			codes.addAll(timesheet.getExceptions().stream()
					.map(Timesheet::getJobcode_id)
					.map(l -> l.toString())
					.map(k -> jobcodes.get(k))
					.filter(j -> j != null)
					.map(j -> {
						switch (j.getName()) {
						case "Holiday":
							return AttendanceCode.HOLIDAY;
						case "Paid Vacation":
							return AttendanceCode.VACATION;
						case "Sick Day":
							return AttendanceCode.SICK;
						case "Call-In":
							return AttendanceCode.CALLED_IN;
						case "Personal Day - APPROVED":
							return AttendanceCode.PERSONAL_DAY_APPROVED;
						case "Personal Day - NOT APPROVED":
							return AttendanceCode.PERSONAL_DAY_UNAPPROVED;
						default:
							return null;
						}
					})
					.filter(j -> j != null)
					.collect(Collectors.toList()));
		}
		
		if(codes.contains(AttendanceCode.HOLIDAY)) {
			codes.remove(AttendanceCode.UNPAID_HOLIDAY);
		}

		return codes;
	}
}
