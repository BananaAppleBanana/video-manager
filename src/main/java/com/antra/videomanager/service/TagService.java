package com.antra.videomanager.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TagService {
    Map<?, ?> getTagAndCount();
}

