package edu.epam.fop;

import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class SlangBasedDateConfigurer implements FormatterConfigurer {
    @Override
    public void configure(DateTimeFormatterBuilder builder) {
        Locale.setDefault(Locale.ENGLISH);

        builder.appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral(' ')
                .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
                .appendLiteral(" of ")
                .appendValueReduced(ChronoField.YEAR, 2, 4, 1931);

    }
}
