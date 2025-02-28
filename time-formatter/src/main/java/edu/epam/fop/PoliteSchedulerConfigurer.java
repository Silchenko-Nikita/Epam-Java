package edu.epam.fop;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Map;

public class PoliteSchedulerConfigurer implements FormatterConfigurer {
    @Override
    public void configure(DateTimeFormatterBuilder builder) {
        Locale.setDefault(Locale.ENGLISH);

        builder
                .appendLiteral("Scheduled on ")
                .appendText(ChronoField.DAY_OF_WEEK, TextStyle.FULL)
                .appendLiteral(" at ")
                .appendValue(ChronoField.CLOCK_HOUR_OF_AMPM)
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                .appendLiteral(" ")
                .optionalStart()
                .appendText(ChronoField.AMPM_OF_DAY, Map.of(0L, "in the morning", 1L, "in the afternoon"))
                .optionalEnd()
                .optionalStart()
                .appendLiteral(" by ")
                .appendZoneText(TextStyle.FULL)
                .optionalEnd();
    }
}
