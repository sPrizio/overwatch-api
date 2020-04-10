package com.r6overwatch.overwatchapi.repositories.impl;

import com.r6overwatch.overwatchapi.models.entities.OverwatchEntity;
import com.r6overwatch.overwatchapi.repositories.OverwatchRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of {@link OverwatchRepository}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
public class OverwatchRepositoryImpl implements OverwatchRepository {

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    @Override
    public void refresh(OverwatchEntity entity) {
        this.entityManager.refresh(this.entityManager.merge(entity));
    }
}
