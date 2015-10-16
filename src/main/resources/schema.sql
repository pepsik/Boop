DROP TABLE ACCOUNT IF EXISTS;
CREATE TABLE ACCOUNT (
  account_id INTEGER IDENTITY PRIMARY KEY,
  username varchar(45) NOT NULL,
  password varchar(60) NOT NULL
);
DROP TABLE COMMENT IF EXISTS;
CREATE TABLE COMMENT (
  comment_id INTEGER IDENTITY PRIMARY KEY,
  text varchar(2000) NOT NULL,
  owner_id INTEGER NOT NULL,
  post_id INTEGER NOT NULL,
  date TIMESTAMP NOT NULL
);
DROP TABLE POST IF EXISTS;
CREATE TABLE POST (
  post_id INTEGER IDENTITY PRIMARY KEY,
  title varchar(100) DEFAULT NULL,
  text varchar(10000) NOT NULL,
  date TIMESTAMP NOT NULL,
  owner_id INTEGER NOT NULL
);
