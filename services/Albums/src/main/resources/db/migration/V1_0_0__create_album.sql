CREATE TABLE ALBUMS (
   id INT NOT NULL,
   ALBUM_ID VARCHAR(50) NOT NULL,
   USER_ID VARCHAR(50) NOT NULL,
   NAME VARCHAR(50) NOT NULL,
   DESCRIPTION VARCHAR(250) NOT NULL
);

INSERT INTO ALBUMS VALUES (1, 'album1Id', '6d02f3b1-531b-4b0e-b28e-63e431e8be0a', 'album 1 name', 'album 1 description');
INSERT INTO ALBUMS VALUES (2, 'album2Id', '6d02f3b1-531b-4b0e-b28e-63e431e8be0a', 'album 2 name', 'album 2 description');