package com.nwidart.techvideo.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.repository.SessionRepository;
import java.time.OffsetDateTime;
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
public class SessionControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private SessionRepository sessionRepository;

  private OffsetDateTime now = OffsetDateTime.now();

  @Before
  public void setUp() throws Exception {
    sessionRepository.save(new Session(now));
  }

  @Test
  public void itReturnsAListOfSessions() {
    final ResponseEntity<Session[]> responseEntity = restTemplate.getForEntity("/api/v1/sessions", Session[].class);
    final Session[] body = responseEntity.getBody();
    final Session session = body[0];

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(session.getDate().toInstant()).isEqualTo(now.toInstant());
  }
}
