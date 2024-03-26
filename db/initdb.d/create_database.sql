CREATE DATABASE IF NOT EXISTS membership;
CREATE DATABASE IF NOT EXISTS banking;

CREATE TABLE membership.membership
(
    membership_id  bigint not null auto_increment,
    name           varchar(255),
    address        varchar(255),
    email          varchar(255),
    is_corporation bit    not null,
    is_valid       bit    not null,
    primary key (membership_id)
) engine=InnoDB