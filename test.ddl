create
or replace table boot.board
(
    board_no      int auto_increment
        primary key,
    board_title   varchar(100)  null,
    board_content varchar(500)  null,
    board_date    datetime      null,
    board_read    int default 0 null
);

create
or replace table boot.member
(
    member_no int auto_increment
        primary key,
    member_id varchar(200)     not null,
    member_pw varchar(200)     not null,
    delete_yn char default 'N' not null,
    constraint member_id
        unique (member_id)
);

create
or replace table boot.auth
(
    member_id   varchar(200) not null,
    member_auth varchar(100) not null,
    constraint auth_member_member_id_fk
        foreign key (member_id) references boot.member (member_id)
);