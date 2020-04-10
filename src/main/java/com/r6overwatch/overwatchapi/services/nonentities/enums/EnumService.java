package com.r6overwatch.overwatchapi.services.nonentities.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service-layer that handles enum conversions and validations
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class EnumService<E extends Enum<E>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumService.class);


    //  METHODS

    /**
     * Validates an enum for the given string and enum class
     *
     * @param clazz type of enum to be used for validation
     * @param string enum string
     * @return true if the enum string maps to a value for the given enum class
     */
    public boolean isValidEnum(Class<E> clazz, String string) {
        try {
            E.valueOf(clazz, string);
            return true;
        } catch (Exception e) {
            LOGGER.error("Invalid enum for string {} and enum type {}", string, clazz);
            return false;
        }
    }

    /**
     * Gets the enum for the given enum class and string
     *
     * @param clazz type of enum to be used for conversion
     * @param string enum string
     * @return enum value for string if correctly mapped, null otherwise
     */
    public E getEnum(Class<E> clazz, String string) {
        if (isValidEnum(clazz, string)) {
            return E.valueOf(clazz, string);
        }

        return null;
    }
}
