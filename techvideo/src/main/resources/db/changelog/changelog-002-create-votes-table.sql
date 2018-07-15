--liquibase formatted sql

--changeset nwidart:create-votes-table

CREATE TABLE votes
(
  id       int PRIMARY KEY AUTO_INCREMENT,
  email    varchar(255),
  video_id int,
  CONSTRAINT votes_fk_video_id FOREIGN KEY (video_id) REFERENCES videos (id)
);
