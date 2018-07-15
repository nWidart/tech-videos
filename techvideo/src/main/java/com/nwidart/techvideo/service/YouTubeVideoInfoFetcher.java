package com.nwidart.techvideo.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YouTubeVideoInfoFetcher {

  @Value("${youtube.apikey}")
  private String youtubeApiKey;

  SearchListResponse fetch(String videoId) {
    SearchListResponse searchResponse = null;
    YouTube youtube = getYouTube();
    try {
      YouTube.Search.List search = youtube.search().list("id,snippet");
      search.setKey(youtubeApiKey);
      search.setQ(videoId);
      search.setType("video");
      search.setMaxResults(1L);
      searchResponse = search.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return searchResponse;
  }

  private YouTube getYouTube() {
    return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
        (request) -> {
        }).setApplicationName("tech-video").build();
  }
}
