package com.r6overwatch.overwatchapi.controllers;

import com.r6overwatch.overwatchapi.controllers.response.StandardJsonResponse;
import com.r6overwatch.overwatchapi.exceptions.OverwatchEntityNotFoundException;
import com.r6overwatch.overwatchapi.resources.entities.OverwatchResource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A parent controller that holds common variables and methods for the API
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public abstract class AbstractOverwatchController<R extends OverwatchResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractOverwatchController.class);


    //  METHODS

    /**
     * A generic find method that will determine whether the entity could be found
     *
     * @param id id of entity
     * @param resource entity as a result of calling find()
     * @return response based on whether the entity was found
     */
    protected StandardJsonResponse findEntity(Long id, R resource) {
        try {
            if (resource.isPresent()) {
                return new StandardJsonResponse(true, resource, StringUtils.EMPTY);
            } else {
                throw new OverwatchEntityNotFoundException("The selected entity could not be found.");
            }
        } catch (OverwatchEntityNotFoundException e) {
            LOGGER.error("Entity with id {} could not be found", id);
            return new StandardJsonResponse(false, null, e.getMessage());
        }
    }
}
