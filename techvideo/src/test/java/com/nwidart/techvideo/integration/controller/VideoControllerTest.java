package com.nwidart.techvideo.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.repository.VideoRepository;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VideoControllerTest {

  @Autowired
  TestRestTemplate restTemplate;
  @Autowired
  VideoRepository videoRepository;

  @Before
  public void setUp() throws Exception {
    videoRepository.deleteAll();
    videoRepository.save(new Video("title", "url"));
  }

  @Test
  public void testItReturnsAListOfVideos() {
    final ResponseEntity<Video[]> responseEntity = restTemplate.getForEntity("/api/v1/videos", Video[].class);
    final Video[] body = responseEntity.getBody();
    final Video video = body[0];

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(video.getTitle()).isEqualTo("title");
    assertThat(video.getUrl()).isEqualTo("url");
  }

  @Test
  public void itCanCreateAVideo() {
    HashMap<String, String> requestBody = new HashMap<>();
    requestBody.put("url", "my/url");
    requestBody.put("title", "my title");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<HashMap> entity = new HttpEntity<>(requestBody, headers);

    final ResponseEntity<Video> response = restTemplate.postForEntity("/api/v1/videos", entity, Video.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody().getTitle()).isEqualTo("my title");
    assertThat(response.getBody().getUrl()).isEqualTo("my/url");
  }
}
