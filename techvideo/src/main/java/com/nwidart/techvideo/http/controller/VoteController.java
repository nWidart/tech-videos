package com.nwidart.techvideo.http.controller;

import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.service.VoteService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

  private VoteService voteService;

  public VoteController(VoteService voteService) {
    this.voteService = voteService;
  }

  @GetMapping
  public List<Vote> index() {
    return voteService.all();
  }

  @GetMapping("submit")
  public Map<String, String> submitVote(@RequestParam("videoId") Integer videoId) {
    voteService.submitNewVote(videoId);

    return Collections.singletonMap("response", "Your vote was submitted. Thank you.");
  }
}
