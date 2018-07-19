package com.nwidart.techvideo.http.controller;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.service.SessionService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

  private SessionService sessionService;

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @GetMapping
  public List<Session> index() {
    return sessionService.all();
  }
}
