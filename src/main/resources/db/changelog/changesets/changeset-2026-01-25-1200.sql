--liquibase formatted sql

--changeset blog:drop-author-id-column
ALTER TABLE article DROP COLUMN author_id;
