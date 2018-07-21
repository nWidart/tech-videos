package com.nwidart.techvideo.service;

import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.http.requests.CreateVoteRequest;
import com.nwidart.techvideo.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

  private VoteRepository voteRepository;

  public VoteService(VoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  public Vote submitNewVote(CreateVoteRequest request) {
    return voteRepository.save(request.toModel());
  }
}
