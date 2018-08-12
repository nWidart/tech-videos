package com.nwidart.techvideo.http.controller;

import com.nwidart.techvideo.dto.ErrorDetails;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.exception.SessionNotFound;
import com.nwidart.techvideo.exception.VideoNotFound;
import com.nwidart.techvideo.service.SessionService;
import com.nwidart.techvideo.service.VideoService;
import com.nwidart.techvideo.service.VoteService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

  private final VideoService videoService;
  private final SessionService sessionService;
  private VoteService voteService;

  public VoteController(VoteService voteService, VideoService videoService, SessionService sessionService) {
    this.voteService = voteService;
    this.videoService = videoService;
    this.sessionService = sessionService;
  }

  @CrossOrigin
  @GetMapping
  public List<Vote> index(@RequestParam(value = "sessionId", required = false) Integer sessionId) {
    if (sessionId != null) {
      return voteService.allForSession(sessionId);
    }
    return voteService.all();
  }

  @CrossOrigin
  @GetMapping("results")
  public List<Vote> results(@RequestParam(value = "sessionId") Integer sessionId) {
    return voteService.countVotesForSession(sessionId);
  }

  @CrossOrigin
  @GetMapping("submit")
  public Map<String, String> submitVote(@RequestParam("videoId") Integer videoId,
      @RequestParam("sessionId") Integer sessionId) throws VideoNotFound, SessionNotFound {
    videoService.findById(videoId).orElseThrow(() -> new VideoNotFound("The video was not found"));
    sessionService.findById(sessionId).orElseThrow(() -> new SessionNotFound("The session was not found"));

    voteService.submitNewVote(videoId, sessionId);

    return Collections.singletonMap("response", "Your vote was submitted. Thank you.");
  }

  @CrossOrigin
  @GetMapping("retract")
  public Map<String, String> retractVote(@RequestParam("videoId") Integer videoId,
      @RequestParam("sessionId") Integer sessionId) {
    voteService.retractVote(videoId, sessionId);

    return Collections.singletonMap("response", "Your vote was retracted.");
  }

  @ExceptionHandler(VideoNotFound.class)
  public ResponseEntity<ErrorDetails> handleVideoNotFoundException(VideoNotFound videoNotFound, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), videoNotFound.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SessionNotFound.class)
  public ResponseEntity<ErrorDetails> handleSessionNotFoundException(SessionNotFound videoNotFound,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), videoNotFound.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
