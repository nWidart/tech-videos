package com.nwidart.techvideo.unit.service;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.repository.VideoRepository;
import com.nwidart.techvideo.repository.VoteRepository;
import com.nwidart.techvideo.repository.vote.CustomVoteRepository;
import com.nwidart.techvideo.service.VoteService;
import java.util.List;
import java.util.Optional;
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
  @Mock
  VideoRepository videoRepository;
  @Mock
  private CustomVoteRepository customVoteRepository;

  private VoteService voteService;

  @Before
  public void setUp() throws Exception {
    this.voteService = new VoteService(voteRepository, videoRepository);
  }

  @Test
  public void itCanSubmitANewVote() {
    Mockito
        .when(voteRepository.save(ArgumentMatchers.any(Vote.class)))
        .thenReturn(new Vote(new Video("My video", "youtube.com/myvideo")));

    Vote vote = voteService.submitNewVote(1, 1);

    Mockito.verify(voteRepository).save(ArgumentMatchers.any(Vote.class));
    Assert.assertEquals("My video", vote.getVideo().getTitle());
  }

  @Test
  public void itCanRetractAVote() {
    Video video = new Video("My video", "youtube.com/myvideo");
    video.setId(1);
    Mockito
        .when(voteRepository.findFirstByVideoAndSessionId(video, 1))
        .thenReturn(new Vote(video));
    Mockito
        .when(videoRepository.findById(video.getId()))
        .thenReturn(Optional.of(video));

    voteService.retractVote(1, 1);

    Mockito.verify(voteRepository).delete(ArgumentMatchers.any(Vote.class));
  }

  @Test
  public void allReturnsAllVotes() {
    Mockito.when(voteRepository.findAll()).thenReturn(listOfVotes(1));

    List<Vote> votes = voteService.all();

    Mockito.verify(voteRepository).findAll();
    Assert.assertEquals(2, votes.size());
  }

  @Test
  public void allForSessionReturnsCorrectVotes() {
    Mockito.when(voteRepository.findAllBySessionId(1)).thenReturn(listOfVotes(1));

    Assert.assertEquals(2, voteService.allForSession(1).size());
    Mockito.verify(voteRepository).findAllBySessionId(ArgumentMatchers.anyInt());
  }

  @Test
  public void countVotesForSession() {
//    Mockito
//        .when(voteRepository.votesForSessionCount(1))
//        .thenReturn(listOfVotes(1));

    // voteService.votesForSessionCount(1);
  }

  private List<Vote> listOfVotes(int sessionId) {
    return List.of(
        new Vote(new Video("title", "url"), sessionId),
        new Vote(new Video("title 2", "url 2"), sessionId)
    );
  }
}
