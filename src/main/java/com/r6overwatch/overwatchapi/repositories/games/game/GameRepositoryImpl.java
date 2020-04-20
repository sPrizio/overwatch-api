package com.r6overwatch.overwatchapi.repositories.games.game;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of {@link GameRepositoryCustom}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
public class GameRepositoryImpl implements GameRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
}
