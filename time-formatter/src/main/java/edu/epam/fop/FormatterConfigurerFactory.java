package edu.epam.fop;

public class FormatterConfigurerFactory {
  
  private FormatterConfigurerFactory() {}

  public static FormatterConfigurer slangBasedDate() {
    return new SlangBasedDateConfigurer();
  }
  
  public static FormatterConfigurer politeScheduler() {
    return new PoliteSchedulerConfigurer();
  }
  
  public static FormatterConfigurer scientificTime() {
    return new ScientificTimeConfigurer();
  }
  
  public static FormatterConfigurer historicalCalendar() {
    return new HistoricalCalendarConfigurer();
  }
}
