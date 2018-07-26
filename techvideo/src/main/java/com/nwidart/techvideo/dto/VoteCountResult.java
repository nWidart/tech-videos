package com.nwidart.techvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteCountResult {

  private String count;
  private String title;
  private String url;
}
