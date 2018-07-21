package com.nwidart.techvideo.http.requests;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteRequest {

  private Integer videoId;

  public Vote toModel() {
    Vote vote = new Vote();
    vote.setVideo(new Video(videoId));
    return vote;
  }
}
