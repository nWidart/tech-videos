--liquibase formatted sql

--changeset nwidart:create-videos-table

CREATE TABLE videos
(
  id         int PRIMARY KEY AUTO_INCREMENT,
  title      varchar(255) NOT NULL,
  url        varchar(255) NOT NULL,
  session_id int,
  CONSTRAINT videos_fk_session_id FOREIGN KEY (session_id) REFERENCES sessions (id)
);
