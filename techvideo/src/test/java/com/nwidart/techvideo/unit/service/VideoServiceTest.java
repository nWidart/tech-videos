package com.nwidart.techvideo.unit.service;

import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.http.requests.CreateVideoRequest;
import com.nwidart.techvideo.repository.VideoRepository;
import com.nwidart.techvideo.service.VideoService;
import com.nwidart.techvideo.service.YouTubeUrlParser;
import com.nwidart.techvideo.service.YouTubeVideoInfoFetcher;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VideoServiceTest {

  @Mock
  private VideoRepository videoRepository;
  @Mock
  private YouTubeVideoInfoFetcher youTubeVideoInfoFetcher;
  @Mock
  private YouTubeUrlParser youTubeUrlParser;

  private VideoService videoService;

  @Before
  public void setUp() throws Exception {
    this.videoService = new VideoService(videoRepository, youTubeVideoInfoFetcher, youTubeUrlParser);
  }

  @Test
  public void allReturnsAListOfVideos() {
    Mockito.when(videoRepository.findAll()).thenReturn(listOfVideos());

    List<Video> videos = videoService.all();

    Mockito.verify(videoRepository).findAll();
    Assert.assertEquals(2, videos.size());
    Assert.assertEquals("Video One", videos.get(0).getTitle());
    Assert.assertEquals("Video Two", videos.get(1).getTitle());
  }

  @Test
  public void findByIdCallsTheRepository() {
    videoService.findById(1);
    Mockito.verify(videoRepository).findById(1);
  }

  @Test
  public void createStoresAVideoWithCustomTitle() throws Exception {
    Mockito
        .when(videoRepository.save(ArgumentMatchers.any(Video.class)))
        .thenReturn(new Video("Custom title", "123"));

    Video video = videoService.create(new CreateVideoRequest("123", "Custom title", ""));

    Assert.assertEquals("Custom title", video.getTitle());
    Assert.assertEquals("123", video.getUrl());
  }

  @Test
  public void createStoresAVideoWithYouTubeTitle() throws Exception {
    Mockito
        .when(youTubeUrlParser.getVideoId(ArgumentMatchers.anyString()))
        .thenReturn("tVRzlh_73ws");
    Mockito
        .when(youTubeVideoInfoFetcher.fetch("tVRzlh_73ws"))
        .thenReturn(searchListResponse());

    Mockito
        .when(videoRepository.save(ArgumentMatchers.any(Video.class)))
        .thenReturn(new Video("YouTube Video Title", "https://www.youtube.com/watch?v=tVRzlh_73ws"));

    Video video = videoService.create(new CreateVideoRequest("https://www.youtube.com/watch?v=tVRzlh_73ws"));

    Assert.assertEquals("YouTube Video Title", video.getTitle());
    Assert.assertEquals("https://www.youtube.com/watch?v=tVRzlh_73ws", video.getUrl());
  }

  private List<Video> listOfVideos() {
    return List.of(new Video("Video One", "1"), new Video("Video Two", "2"));
  }

  private SearchListResponse searchListResponse() {
    SearchResultSnippet snippet = new SearchResultSnippet().setTitle("YouTube Video Title");
    List<SearchResult> searchResults = List.of(new SearchResult().setSnippet(snippet));

    return new SearchListResponse().setItems(searchResults);
  }
}
