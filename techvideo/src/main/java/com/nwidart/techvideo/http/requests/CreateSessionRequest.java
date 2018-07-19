package com.nwidart.techvideo.http.requests;

import com.nwidart.techvideo.entity.Session;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.Data;

@Data
public class CreateSessionRequest {

  private Date date;

  public Session toModel() {
    return new Session(OffsetDateTime.ofInstant(date.toInstant(), ZoneId.of("Europe/Paris")));
  }
}
