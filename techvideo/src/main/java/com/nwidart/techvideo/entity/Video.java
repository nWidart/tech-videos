package com.nwidart.techvideo.entity;

import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(
    name = "videos",
    uniqueConstraints = {
        @UniqueConstraint(name = "title", columnNames = "title")
    }
)
public class Video {

  @Id
  @GeneratedValue
  private Integer id;
  private String title;
  private String url;
  private OffsetDateTime viewedAt;

  public Video() {
  }

  public Video(String title, String url) {
    this.title = title;
    this.url = url;
  }
}
