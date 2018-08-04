package com.nwidart.techvideo.integration.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.nwidart.techvideo.email.NotifyPerson;
import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.repository.SessionRepository;
import java.time.OffsetDateTime;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
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
  @MockBean
  private NotifyPerson notifyPerson;

  private OffsetDateTime now = OffsetDateTime.now();

  @Before
  public void setUp() throws Exception {
    sessionRepository.save(new Session(now));
  }

  @Test
  @DirtiesContext
  public void itReturnsAListOfSessions() {
    final ResponseEntity<Session[]> responseEntity = restTemplate.getForEntity("/api/v1/sessions", Session[].class);
    final Session[] body = responseEntity.getBody();
    final Session session = body[0];

    assertThat(body.length).isEqualTo(1);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(session.getDate().toLocalDate()).isEqualTo(now.toLocalDate());
  }

  @Test
  @DirtiesContext
  public void itCanCreateASession() {
    HashMap<String, String> requestBody = new HashMap<>();
    String date = OffsetDateTime.now().plusDays(1L).toLocalDate().toString();
    requestBody.put("date", date);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<HashMap> entity = new HttpEntity<>(requestBody, headers);

    final ResponseEntity<Session> response = restTemplate.postForEntity("/api/v1/sessions", entity, Session.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody().getDate().toLocalDate()).isEqualTo(date);
  }

  @Test
  @DirtiesContext
  public void itCannotCreateASessionInThePast() {
    HashMap<String, String> requestBody = new HashMap<>();
    requestBody.put("date", OffsetDateTime.now().minusDays(1L).toLocalDate().toString());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<HashMap> entity = new HttpEntity<>(requestBody, headers);

    final ResponseEntity<Session> response = restTemplate.postForEntity("/api/v1/sessions", entity, Session.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }
}
