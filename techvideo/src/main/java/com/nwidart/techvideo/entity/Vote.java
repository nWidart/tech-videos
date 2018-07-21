package com.nwidart.techvideo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "votes")
public class Vote {

  @Id
  @GeneratedValue
  private Integer id;
  @ManyToOne
  private Video video;

  public Vote() {
  }
}
