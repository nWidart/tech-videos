package com.nwidart.techvideo.repository;

import com.nwidart.techvideo.entity.Video;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface VideoRepository extends JpaRepository<Video, Integer> {

  @Query(value = "select v from Video v where session is null")
  List<Video> findAllVideosToVoteOn();
}
