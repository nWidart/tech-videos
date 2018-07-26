package com.nwidart.techvideo.service;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.repository.VoteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService {

  private VoteRepository voteRepository;

  public VoteService(VoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  @Transactional
  public Vote submitNewVote(Integer videoId, Integer sessionId) {
    Vote vote = new Vote();
    vote.setVideo(new Video(videoId));
    vote.setSessionId(sessionId);

    return voteRepository.save(vote);
  }

  public List<Vote> all() {
    return voteRepository.findAll();
  }

  public List<Vote> allForSession(Integer sessionId) {
    return voteRepository.findAllBySessionId(sessionId);
  }

  public List<Vote> countVotesForSession(Integer sessionId) {
    return voteRepository.votesForSessionCount(sessionId);
  }

}
