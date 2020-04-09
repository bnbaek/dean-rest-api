create table account
(
  account_id bigserial   not null,
  username   varchar(30) not null,
  name       varchar(30) not null,
  primary key (account_id)
);

alter table account
  add constraint UK_gex1lmaqpg0ir5g1f5eftyaa1 unique (username);