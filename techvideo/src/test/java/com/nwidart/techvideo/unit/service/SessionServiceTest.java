package com.nwidart.techvideo.unit.service;

import com.nwidart.techvideo.email.NotifyPerson;
import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.http.requests.CreateSessionRequest;
import com.nwidart.techvideo.repository.SessionRepository;
import com.nwidart.techvideo.repository.VideoRepository;
import com.nwidart.techvideo.service.SessionService;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
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
public class SessionServiceTest {

  private final OffsetDateTime now = OffsetDateTime.now();
  @Mock
  private SessionRepository sessionRepository;
  @Mock
  private VideoRepository videoRepository;
  @Mock
  private NotifyPerson notifyPerson;

  private SessionService sessionService;

  @Before
  public void setUp() throws Exception {
    this.sessionService = new SessionService(sessionRepository, notifyPerson, videoRepository);
  }

  @Test
  public void allReturnsAListOfSessions() {
    Mockito.when(sessionRepository.findAll()).thenReturn(listOfSessions());

    List<Session> sessions = sessionService.all();

    Mockito.verify(sessionRepository).findAll();
    Assert.assertEquals(2, sessions.size());
  }

  @Test
  public void createStoresANewSession() throws Exception {
    Mockito
        .when(sessionRepository.save(ArgumentMatchers.any(Session.class)))
        .thenReturn(new Session(now));

    Session session = sessionService.create(
        new CreateSessionRequest(new SimpleDateFormat("yyyy-MM-dd").parse(now.toLocalDate().toString()))
    );

    Mockito.verify(sessionRepository).save(ArgumentMatchers.any(Session.class));
    Mockito.verify(notifyPerson)
        .send(ArgumentMatchers.any(), ArgumentMatchers.any(Session.class), ArgumentMatchers.any());
    Assert.assertEquals(now.toLocalDate(), session.getDate().toLocalDate());
  }

  private List<Session> listOfSessions() {
    return List.of(new Session(OffsetDateTime.now()), new Session(OffsetDateTime.now()));
  }
}
