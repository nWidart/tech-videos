package com.nwidart.techvideo.repository;

import com.nwidart.techvideo.entity.Video;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

  List<Video> findAllBySessionIsNull();
}
