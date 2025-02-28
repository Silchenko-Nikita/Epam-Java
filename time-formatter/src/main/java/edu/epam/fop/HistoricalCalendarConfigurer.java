package edu.epam.fop;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class HistoricalCalendarConfigurer implements FormatterConfigurer {
    @Override
    public void configure(DateTimeFormatterBuilder builder) {
        Locale.setDefault(Locale.ENGLISH);

        builder
                .appendValue(java.time.temporal.ChronoField.YEAR_OF_ERA)
                .appendLiteral(" of ")
                .appendText(java.time.temporal.ChronoField.ERA, TextStyle.FULL)
                .appendLiteral(" (")
                .appendChronologyText(TextStyle.FULL)
                .appendLiteral(")");

    }
}
