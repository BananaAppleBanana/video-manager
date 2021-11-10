package com.antra.videomanager.domain.mapper;

import com.antra.videomanager.domain.dto.VideoDTO;
import com.antra.videomanager.domain.entity.Video;
import com.antra.videomanager.domain.request.VideoRequest;
import com.antra.videomanager.tool.mapper.Mapper;
import com.antra.videomanager.tool.mapper.Mapping;

@Mapper
public interface VideoMapper {

    @Mapping(target = Video.class)
    Object convertVideoRequestToVideo(VideoRequest videoRequest);

    @Mapping(target = VideoDTO.class)
    Object convertVideoToVideoDTO(Video video);
}
