-- Drops all existing views, tables, sequences, schemas, function aliases, roles, user-defined aggregate functions,
-- domains, and users (except the current user). If DELETE FILES is specified, the database files will be removed
-- when the last user disconnects from the database.
DROP ALL OBJECTS DELETE FILES;

CREATE TABLE book (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  title            VARCHAR(255),
  author           VARCHAR(255),
  read             BOOLEAN        NOT NULL DEFAULT 0,
  user             BIGINT         NOT NULL
);

CREATE TABLE user (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  username         VARCHAR(32)       UNIQUE NOT NULL,
  password         TINYBLOB,
  email            VARCHAR(255),
  role             VARCHAR(255)              NOT NULL
);

CREATE TABLE authentication (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  token      VARCHAR(255)      UNIQUE NOT NULL,
  user       BIGINT                   NOT NULL,

  FOREIGN KEY (user) REFERENCES user(id) ON DELETE CASCADE
);
