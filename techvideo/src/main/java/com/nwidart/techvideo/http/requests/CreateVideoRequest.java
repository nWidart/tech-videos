package com.nwidart.techvideo.http.requests;

import com.nwidart.techvideo.entity.Video;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateVideoRequest {

  @NotNull
  private String url;
  private String title;

  public CreateVideoRequest(@NotNull String url) {
    this.url = url;
  }

  public Video toModel() {
    return new Video(title, url);
  }
}
