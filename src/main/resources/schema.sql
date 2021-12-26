drop table if exists genre cascade;
create table genre
(
    id    int primary key auto_increment,
    genre varchar(60)
);

drop table if exists author cascade;
create table author
(
    id   int primary key auto_increment,
    name varchar(60)
);

drop table if exists book cascade;
create table book
(
    id int primary key auto_increment,
    title varchar(60),
    genre_id int(60),
    author_id int(60),
    foreign key (genre_id) references genre(id) on delete cascade,
    foreign key (author_id) references author(id) on delete cascade
)


