package com.nwidart.techvideo.unit.service;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.http.requests.CreateVoteRequest;
import com.nwidart.techvideo.repository.VoteRepository;
import com.nwidart.techvideo.service.VoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

  @Mock
  private VoteRepository voteRepository;

  private VoteService voteService;

  @Before
  public void setUp() throws Exception {
    this.voteService = new VoteService(voteRepository);
  }

  @Test
  public void itCanSubmitANewVote() {
    Mockito
        .when(voteRepository.save(ArgumentMatchers.any(Vote.class)))
        .thenReturn(new Vote(1, new Video("My video", "youtube.com/myvideo")));

    Vote vote = voteService.submitNewVote(new CreateVoteRequest(1));

    Mockito.verify(voteRepository).save(ArgumentMatchers.any(Vote.class));
    Assert.assertEquals("My video", vote.getVideo().getTitle());
  }
}
