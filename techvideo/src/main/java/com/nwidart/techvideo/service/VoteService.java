package com.nwidart.techvideo.service;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.repository.VoteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

  private VoteRepository voteRepository;

  public VoteService(VoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  public Vote submitNewVote(Integer videoId) {
    Vote vote = new Vote();
    vote.setVideo(new Video(videoId));

    return voteRepository.save(vote);
  }

  public List<Vote> all() {
    return voteRepository.findAll();
  }
}
