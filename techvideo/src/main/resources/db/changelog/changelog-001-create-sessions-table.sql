--liquibase formatted sql

--changeset nwidart:create-sessions-table

CREATE TABLE sessions
(
  id   int PRIMARY KEY AUTO_INCREMENT,
  date timestamp NOT NULL default CURRENT_TIMESTAMP
);
