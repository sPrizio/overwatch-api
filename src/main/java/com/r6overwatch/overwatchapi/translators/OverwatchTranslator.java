package com.r6overwatch.overwatchapi.translators;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;

import java.util.Map;

/**
 * Translator interface for Overwatch. Translators are classes used to convert a map of input parameters into the appropriate Java class for use within the service-layer
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface OverwatchTranslator<E extends OverwatchEntity> {

    /**
     * Translates a map of values into the appropriate entity
     *
     * @param values entity values
     * @return new entity containing values
     */
    E translate(Map<String, Object> values);
}
