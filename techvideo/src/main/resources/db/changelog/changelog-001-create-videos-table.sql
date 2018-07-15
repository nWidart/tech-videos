--liquibase formatted sql

--changeset nwidart:create-videos-table

CREATE TABLE videos
(
  id        int PRIMARY KEY AUTO_INCREMENT,
  title     varchar(255) NOT NULL,
  url       varchar(255) NOT NULL,
  viewed_at timestamp    NULL DEFAULT NULL
);
