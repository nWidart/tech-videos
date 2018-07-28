package com.nwidart.techvideo.entity;

import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "sessions")
public class Session {

  @Id
  @GeneratedValue
  private Integer id;
  private OffsetDateTime date;
  @OneToOne(mappedBy = "session")
  private Video video;

  public Session() {
  }

  public Session(OffsetDateTime date) {
    this.date = date;
  }
}
