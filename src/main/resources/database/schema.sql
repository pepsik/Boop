CREATE DATABASE IF NOT EXISTS smartsite;

CREATE TABLE IF NOT EXISTS accounts (
  id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(20) NOT NULL,
  fullname   VARCHAR(40),
  password   VARCHAR(20) NOT NULL,
  birth_date DATE        NOT NULL
);

CREATE TABLE IF NOT EXISTS threads (
  id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id  INT           NOT NULL,
  title       VARCHAR(20)   NOT NULL,
  text        VARCHAR(2000) NOT NULL,
  posted_time DATE          NOT NULL,
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE IF NOT EXISTS posts (
  id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tread_id    INT           NOT NULL,
  account_id  INT           NOT NULL,
  text        VARCHAR(1000) NOT NULL,
  posted_time DATE          NOT NULL,
  FOREIGN KEY (tread_id) REFERENCES threads (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE IF NOT EXISTS roles (
  ID   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts_authority (
  account_id INT NOT NULL,
  role_id    INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES accounts (id),
  FOREIGN KEY (role_id) REFERENCES roles (ID)
);
