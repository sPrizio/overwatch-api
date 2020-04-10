package com.r6overwatch.overwatchapi.exceptions;

/**
 * An exception for entities that could not be found in the Overwatch system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public class OverwatchEntityNotFoundException extends Exception {

    /**
     * Inheriting constructor
     *
     * @param message exception message
     */
    public OverwatchEntityNotFoundException(String message) {
        super(message);
    }
}
