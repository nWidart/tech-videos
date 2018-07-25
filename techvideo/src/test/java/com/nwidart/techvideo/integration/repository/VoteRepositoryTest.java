package com.nwidart.techvideo.integration.repository;

import com.nwidart.techvideo.entity.Session;
import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import com.nwidart.techvideo.repository.VoteRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryTest {

  @Autowired
  private VoteRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void findAllBySessionId() {
    Session session1 = testEntityManager.persistAndFlush(new Session(OffsetDateTime.now()));
    Session session2 = testEntityManager.persistAndFlush(new Session(OffsetDateTime.now()));
    Video video = testEntityManager.persistAndFlush(new Video("My Title", "youtube.com/mytitle"));
    testEntityManager.persistAndFlush(new Vote(video, session1.getId()));
    testEntityManager.persistAndFlush(new Vote(video, session2.getId()));

    List<Vote> votes = repository.findAllBySessionId(session1.getId());
    Assert.assertEquals(1, votes.size());
  }
}
