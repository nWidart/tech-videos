package com.nwidart.techvideo.http.controller;

import com.nwidart.techvideo.entity.Video;
import com.nwidart.techvideo.http.requests.CreateVideoRequest;
import com.nwidart.techvideo.service.VideoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/videos")
public class VideoController {

  private final VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public List<Video> index() {
    return this.videoService.all();
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Video create(@Valid @RequestBody CreateVideoRequest request) throws Exception {
    return this.videoService.create(request);
  }
}
