package com.epam.rd.autotasks.timing;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class SchedulingAssistantImpl implements SchedulingAssistant {
    Collection<Developer> team;
    LocalDate today;

    private static final Map<String, String> CITY_TO_TIMEZONE = Map.of(
            "Los Angeles", "America/Los_Angeles",
            "New York", "America/New_York",
            "London", "Europe/London",
            "Paris", "Europe/Paris",
            "Samara", "Europe/Samara",
            "Prague", "Europe/Prague",
            "Tbilisi", "Asia/Tbilisi"
    );

    public SchedulingAssistantImpl(Collection<Developer> team, LocalDate today) {
        this.team = team;
        this.today = today;
    }

    @Override
    public LocalDateTime schedule(long meetingDurationMinutes, MeetingTimingPreferences preferences) {
        List<ZonedDateTime> potentialMeetingStartTimes = getPossibleMeetingStartTimes(preferences);

        List<ZonedDateTime> validTimes = potentialMeetingStartTimes.stream()
                .filter(start -> isMeetingPossibleForAll(start, meetingDurationMinutes))
                .collect(Collectors.toList());

        if (validTimes.isEmpty()) {
            return null;
        }

        if (preferences.inPeriod == MeetingTimingPreferences.InPeriodPreference.EARLIEST) {
            return validTimes.get(0).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        } else {
            return validTimes.get(validTimes.size() - 1).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        }
    }

    private List<ZonedDateTime> getPossibleMeetingStartTimes(MeetingTimingPreferences preferences) {
        List<ZonedDateTime> potentialTimes = new ArrayList<>();

        LocalDate periodStart = switch (preferences.period) {
            case TODAY -> today;
            case TOMORROW -> today.plusDays(1);
            case THIS_WEEK -> today.with(DayOfWeek.MONDAY);
        };

        LocalDate periodEnd = switch (preferences.period) {
            case TODAY -> today;
            case TOMORROW -> today.plusDays(1);
            case THIS_WEEK -> today.with(DayOfWeek.SUNDAY);
        };

        for (LocalDate date = periodStart; !date.isAfter(periodEnd); date = date.plusDays(1)) {
            for (Developer developer : team) {
                ZoneId cityZoneId = ZoneId.of(CITY_TO_TIMEZONE.get(developer.city));
                LocalTime workStart = developer.workDayStartTime;
                ZonedDateTime startOfDay = ZonedDateTime.of(date, workStart, cityZoneId);
                ZonedDateTime endOfDay = startOfDay.plusHours(8);

                while (!startOfDay.isAfter(endOfDay.minusMinutes(30))) {
                    potentialTimes.add(startOfDay);
                    startOfDay = startOfDay.plusMinutes(30);
                }
            }
        }

        return potentialTimes.stream()
                .sorted(Comparator.comparing(ZonedDateTime::toInstant))
                .collect(Collectors.toList());
    }

    private boolean isMeetingPossibleForAll(ZonedDateTime meetingStartTime, long durationMinutes) {
        ZonedDateTime meetingEndTime = meetingStartTime.plusMinutes(durationMinutes);

        for (Developer developer : team) {
            ZoneId cityZoneId = ZoneId.of(CITY_TO_TIMEZONE.get(developer.city));
            ZonedDateTime workStartUTC = ZonedDateTime.of(today, developer.workDayStartTime, cityZoneId)
                    .withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime workEndUTC = workStartUTC.plusHours(8);

            if (meetingStartTime.isBefore(workStartUTC) || meetingEndTime.isAfter(workEndUTC)) {
                return false;
            }
        }

        return true;
    }
}