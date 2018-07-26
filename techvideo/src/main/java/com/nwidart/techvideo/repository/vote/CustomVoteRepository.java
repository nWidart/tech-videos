package com.nwidart.techvideo.repository.vote;

import com.nwidart.techvideo.dto.VoteCountResult;
import java.util.List;

public interface CustomVoteRepository {

  List<VoteCountResult> votesForSessionCount();
}
