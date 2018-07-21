package com.nwidart.techvideo.repository;

import com.nwidart.techvideo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
