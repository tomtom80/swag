CREATE TABLE users
(
   id bigint NOT NULL AUTO_INCREMENT,
   creation_time timestamp NOT NULL,
   email varchar(255) NOT NULL,
   first_name varchar(255) NOT NULL,
   last_name varchar(255) NOT NULL,
   modification_time timestamp NOT NULL,
   username varchar(255) NOT NULL,
   version bigint,
   PRIMARY KEY (id)
)
;


