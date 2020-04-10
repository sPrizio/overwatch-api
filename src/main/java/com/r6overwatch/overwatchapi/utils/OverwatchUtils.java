package com.r6overwatch.overwatchapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * A utility class to handle various miscellaneous tasks
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public class OverwatchUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverwatchUtils.class);

    /**
     * Private constructor to prevent this utility class from being instantiated
     */
    private OverwatchUtils() {
        throw new UnsupportedOperationException("Utility classes should not be instantiated");
    }


    //  METHODS

    /**
     * Attempts to parse a string into an {@link Integer}
     *
     * @param string integer string to be parsed
     * @return will return null if the string cannot be parsed
     */
    public static Integer parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            LOGGER.error("Integer could not be parsed from the given string {}", string);
            return null;
        }
    }

    /**
     * Attempts to parse a string into an {@link Long}
     *
     * @param string long string to be parsed
     * @return will return null if the string cannot be parsed
     */
    public static Long parseLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (Exception e) {
            LOGGER.error("Long could not be parsed from the given string {}", string);
            return null;
        }
    }

    /**
     * Validates that all given objects are non-null
     *
     * @param objects list of objects to validate
     * @return true if every given object is not null
     */
    public static boolean areNonNull(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::isNull);
    }
}
