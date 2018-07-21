package com.nwidart.techvideo.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.repository.VideoRepository;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations = {"classpath:application-test.properties"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VoteControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private VideoRepository videoRepository;

  @Before
  public void setUp() throws Exception {
    videoRepository.save(new Video("My Title", "youtube.com/mytitle"));
  }

  @Test
  public void itCanSubmitAVote() {
    ResponseEntity<SubmitVoteResponse> response = restTemplate
        .getForEntity("/api/v1/votes/submit?videoId=1", SubmitVoteResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getResponse()).isEqualTo("Your vote was submitted. Thank you.");
  }

  @Data
  private static class SubmitVoteResponse {

    private String response;
  }
}
