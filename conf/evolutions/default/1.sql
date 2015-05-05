# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table event (
  id                        bigint not null,
  name                      varchar(255),
  created_at                bigint,
  start_time                bigint,
  end_time                  bigint,
  is_repeating              boolean,
  repeats_every             bigint,
  notifies_user             boolean,
  user_id                   bigint,
  color                     varchar(255),
  constraint pk_event primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  last_logged_in            bigint,
  email_address             varchar(255),
  allows_notifications      boolean,
  time_zone                 integer,
  password_hash             varchar(255),
  auth_token                varchar(255),
  hash_salt                 varchar(255),
  constraint pk_user primary key (id))
;

create sequence event_seq;

create sequence user_seq;

alter table event add constraint fk_event_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_event_user_1 on event (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists event;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists event_seq;

drop sequence if exists user_seq;

