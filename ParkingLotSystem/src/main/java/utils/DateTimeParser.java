package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeParser
{
    public static LocalDateTime parse(String time)
    {
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy", Locale.ENGLISH);
        return LocalDateTime.parse(time,formatter);
    }
}