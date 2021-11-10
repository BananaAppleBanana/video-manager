package com.antra.videomanager.domain.response;

import com.antra.videomanager.domain.Response;

import java.util.List;

public class VideoResponse implements Response{

    private int virtualCount;

    private List<Object> results;

    public VideoResponse() {
    }

    public int getVirtualCount() {
        return virtualCount;
    }

    public VideoResponse setVirtualCount(int virtualCount) {
        this.virtualCount = virtualCount;
        return this;
    }

    public List<Object> getResults() {
        return results;
    }

    public VideoResponse setResults(List<Object> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return "VideoResponse{" +
                "virtualCount=" + virtualCount +
                ", results=" + results +
                '}';
    }
}
