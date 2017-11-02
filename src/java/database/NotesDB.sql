DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;


DROP TABLE if exists User;

CREATE TABLE User( 
    username VARCHAR(10) NOT NULL,
    password VARCHAR(10) NOT NULL,
    email VARCHAR(30) NOT NULL,
    active BIT NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

drop table if exists note;

create table note(
    noteid INT NOT NULL auto_increment,
    datecreated DATETIME NOT NULL,
    content VARCHAR(10000) character set utf8 NOT NULL,
    PRIMARY KEY (noteId));

INSERT INTO User values('admin', 'password', 'test@test.com', 1, 'Bob', 'Bobberson');
INSERT INTO Note (datecreated, content) values(CURDATE(), 'Dummy note 1');
INSERT INTO Note (datecreated, content) values(CURDATE(), 'Dummy note 2');
