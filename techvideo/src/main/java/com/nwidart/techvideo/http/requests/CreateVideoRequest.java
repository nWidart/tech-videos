package com.nwidart.techvideo.http.requests;

import com.nwidart.techvideo.entity.Video;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateVideoRequest {

  @NotNull
  private String url;
  private String title;

  public Video toModel() {
    return new Video(title, url);
  }
}
