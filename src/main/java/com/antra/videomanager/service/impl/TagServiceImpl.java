package com.antra.videomanager.service.impl;

import com.antra.videomanager.domain.entity.VideoTagValue;
import com.antra.videomanager.repository.VideoTagValueRepository;
import com.antra.videomanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TagServiceImpl implements TagService{

    private final VideoTagValueRepository videoTagRepository;

    @Autowired
    public TagServiceImpl(VideoTagValueRepository videoTagRepository) {
        this.videoTagRepository = videoTagRepository;
    }

    @Override
    public Map<String, Long> getTagAndCount() {
        List<VideoTagValue> tags = videoTagRepository.findAll();
        Map<String, Long> payloads = tags.stream().collect(Collectors.groupingBy(VideoTagValue::getTagValue, Collectors.counting()));
        return payloads;
    }
}
