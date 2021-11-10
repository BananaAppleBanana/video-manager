package com.antra.videomanager.tool.mapper.manager;

import org.springframework.stereotype.Component;

@Component
public interface ORMCacheManager {
    void detach(Object obj);
}
