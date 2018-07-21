package com.nwidart.techvideo.http.requests;

import com.nwidart.techvideo.entity.Session;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionRequest {

  private Date date;

  public Session toModel() {
    return new Session(OffsetDateTime.ofInstant(date.toInstant(), ZoneId.of("Europe/Paris")));
  }
}
