package com.nwidart.techvideo.service;

import com.nwidart.techvideo.email.NotifyPerson;
import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.http.requests.CreateSessionRequest;
import com.nwidart.techvideo.repository.SessionRepository;
import com.nwidart.techvideo.repository.VideoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

  private SessionRepository sessionRepository;
  private final VideoRepository videoRepository;
  private NotifyPerson notifyPerson;
  @Value("${email.to}")
  private String to;

  public SessionService(SessionRepository sessionRepository, NotifyPerson notifyPerson,
      VideoRepository videoRepository) {
    this.sessionRepository = sessionRepository;
    this.notifyPerson = notifyPerson;
    this.videoRepository = videoRepository;
  }

  public List<Session> all() {
    return sessionRepository.findAll();
  }

  public Optional<Session> findById(Integer id) {
    return sessionRepository.findById(id);
  }

  @Transactional
  public Session create(CreateSessionRequest request) {
    Session session = sessionRepository.save(request.toModel());

    notifyPerson.send(to, session, videoRepository.findAllVideosToVoteOn());

    return session;
  }
}
