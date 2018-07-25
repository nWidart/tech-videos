--liquibase formatted sql

--changeset nwidart:create-votes-table

CREATE TABLE votes
(
  id         int PRIMARY KEY AUTO_INCREMENT,
  email      varchar(255),
  video_id   int,
  session_id int,
  CONSTRAINT votes_fk_video_id FOREIGN KEY (video_id) REFERENCES videos (id),
  CONSTRAINT votes_fk_session_id FOREIGN KEY (session_id) REFERENCES sessions (id)
) DEFAULT CHARACTER SET utf8mb4;
