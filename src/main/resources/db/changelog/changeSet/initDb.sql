--liquibase formatted sql

--changeset rfatkhutdinov:init-db
create table IF NOT EXISTS limits
(
    id bigserial primary key,
    user_id bigint,
    day_limit numeric,
    balance numeric
);
