CREATE TABLE USER
(
    id       int auto_increment primary key,
    username varchar,
    password varchar
);

CREATE TABLE AUTHORITY
(
    id        int auto_increment primary key,
    authority varchar(15)
);

CREATE TABLE LINK
(
    id         int auto_increment primary key,
    original   varchar,
    short_name varchar,
    count      int,
    user_id    int,
    foreign key (user_id) references USER (id)
);

CREATE TABLE USER_AUTHORITY
(
    user_id      int,
    authority_id int,
    foreign key (user_id) references USER (id),
    foreign key (authority_id) references AUTHORITY (id)
)