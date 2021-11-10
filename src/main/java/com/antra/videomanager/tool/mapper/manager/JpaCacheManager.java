package com.antra.videomanager.tool.mapper.manager;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class JpaCacheManager implements ORMCacheManager{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void detach(Object obj) {
        em.detach(obj);
    }
}
