insert into PUBLIC.GENRE (GENRE) values
                                        ('horror'),
                                        ('drama'),
                                        ('comedy');

insert into PUBLIC.AUTHOR (NAME) values
                                        ('Rockwell'),
                                        ('Mr.Blues'),
                                        ('Sensei');
insert into BOOK (TITLE, GENRE_ID, AUTHOR_ID)
values ('Lets boogy voogy', 1, 1),
       ('Underground', 1, 1),
       ('give me your hand', 2, 2),
       ('not all bad is bad',3, 3);