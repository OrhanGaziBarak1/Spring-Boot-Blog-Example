-- liquibase formatted sql

-- changeset orhangazibarak:add-user-public-id-to-article splitStatements:false
ALTER TABLE article ADD COLUMN user_public_id UUID NOT NULL;