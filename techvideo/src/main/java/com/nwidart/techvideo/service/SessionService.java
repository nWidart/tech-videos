package com.nwidart.techvideo.service;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.repository.SessionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  private SessionRepository sessionRepository;

  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  public List<Session> all() {
    return sessionRepository.findAll();
  }
}
