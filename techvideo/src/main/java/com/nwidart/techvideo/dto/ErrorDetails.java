package com.nwidart.techvideo.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorDetails {

  private Date timestamp;
  private String message;
  private String details;

  public ErrorDetails(Date timestamp, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }
}
