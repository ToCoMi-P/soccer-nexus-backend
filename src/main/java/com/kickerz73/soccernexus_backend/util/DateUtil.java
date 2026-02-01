package com.kickerz73.soccernexus_backend.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String PATTERN_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.of("Europe/Berlin"));

    public static LocalDateTime parseGermanDate(String instant) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDateTime.parse(instant, formatter);
    }
    
    public static LocalDateTime parseGermanDateFull(String instant) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");
        return LocalDateTime.parse(instant, formatter);
    }
    
}
