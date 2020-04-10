package com.r6overwatch.overwatchapi.converters.entities;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;

import java.util.Collection;

/**
 * A converter is a class that converts entities into DTOs for use by API controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface OverwatchConverter<E extends OverwatchEntity, R extends OverwatchResource> {

    /**
     * Converts an entity (object stored the database) into a resource (a dto used by facades)
     *
     * @param entity entity to convert
     * @return newly created resource based off of the entity
     */
    R convert(E entity);

    /**
     * Converts a list of entities into a list of resources
     *
     * @param entity list of entities to convert
     * @return list of resources based off of their respective entities
     */
    Collection<R> convertAll(Collection<E> entity);
}
