package com.nwidart.techvideo.repository;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

  List<Vote> findAllBySessionId(Integer sessionId);

  Vote findFirstByVideoAndSessionId(Video video, Integer sessionId);

  // SELECT video_id, COUNT(votes.id) FROM votes WHERE session_id=2 GROUP BY video_id
//  //List<List<String>> votesForSessionCount(@Param("sessionId") Integer sessionId);
  @Query("select count(v.id) as counter, v.video from Vote v group by v.video order by counter desc")
  List<Vote> votesForSessionCount(@Param("sessionId") Integer sessionId);
}
