package edu.epam.fop;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class ScientificTimeConfigurer implements FormatterConfigurer {
    @Override
    public void configure(DateTimeFormatterBuilder builder) {
        Locale.setDefault(Locale.ENGLISH);

        builder.appendValue(ChronoField.HOUR_OF_DAY, 2) // Exactly 2 symbols for hours
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2) // Exactly 2 symbols for minutes
                .appendLiteral(":")
                .appendValue(ChronoField.SECOND_OF_MINUTE, 2) // Exactly 2 symbols for seconds
                .appendLiteral(".")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false);
    }
}
