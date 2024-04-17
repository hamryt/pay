CREATE DATABASE IF NOT EXISTS membership;
CREATE DATABASE IF NOT EXISTS banking;
CREATE DATABASE IF NOT EXISTS money;

create table membership.membership
(
    corporation   bit    not null,
    valid         bit    not null,
    membership_id bigint not null auto_increment,
    address       varchar(255),
    email         varchar(255),
    name          varchar(255),
    primary key (membership_id)
) engine=InnoDB;

create table banking.registered_bank_account
(
    registered_bank_account_id bigint not null auto_increment,
    linked_status_valid        bit    not null,
    bank_account_number        varchar(255),
    bank_name                  varchar(255),
    membership_id              varchar(255),
    primary key (registered_bank_account_id)
) engine=InnoDB;

create table banking.request_firmbanking
(
    id                 bigint       not null auto_increment,
    from_bank_name     varchar(255) not null,
    from_bank_account  varchar(255) not null,
    to_bank_name       varchar(255) not null,
    to_bank_account    varchar(255) not null,
    money_account      int          not null,
    firmbanking_status int          not null,
    primary key (id)
) engine=InnoDB;

create table money.member_money
(
    id            bigint not null auto_increment,
    membership_id bigint not null,
    balance       int    not null,
    primary key (id)
) engine=InnoDB;

create table money.money_change_request
(
    money_change_request_id bigint       not null auto_increment,
    target_membership_id    bigint       not null,
    money_changing_type     ENUM('INCREASE', 'DECREASE') not null,
    money_amount            int          not null,
    timestamp               timestamp    not null,
    corporation             bit          not null,
    changing_money_status   ENUM('REQUIRED', 'SUCCEEDED', 'FAILED', 'CANCELED') not null,
    uuid                    binary(16)  not null,
    primary key (money_change_request_id)
) engine=InnoDB;