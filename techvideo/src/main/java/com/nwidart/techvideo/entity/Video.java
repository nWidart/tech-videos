package com.nwidart.techvideo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
  @OneToOne
  @JsonBackReference
  private Session session;

  public Video() {
  }

  public Video(String title, String url) {
    this.title = title;
    this.url = url;
  }

  public Video(String title, String url, Session session) {
    this.title = title;
    this.url = url;
    this.session = session;
  }

  public Video(Integer id) {
    this.id = id;
  }
}
