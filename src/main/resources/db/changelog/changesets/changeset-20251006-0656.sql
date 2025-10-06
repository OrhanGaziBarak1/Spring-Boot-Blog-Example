-- liquibase formatted sql

-- changeset orhangazibarak:1759733817216-5 splitStatements:false
CREATE TABLE article (id VARCHAR(255) NOT NULL, author_id BIGINT NOT NULL, content TEXT NOT NULL, created_at TIMESTAMP(6) WITHOUT TIME ZONE, deleted_at TIMESTAMP(6) WITHOUT TIME ZONE, deleted BOOLEAN NOT NULL, updated_at TIMESTAMP(6) WITHOUT TIME ZONE, CONSTRAINT "articlePK" PRIMARY KEY (id));

-- changeset orhangazibarak:1759733817216-1 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'users' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.users drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1759733817216-2 splitStatements:false
ALTER TABLE users ADD CONSTRAINT "usersPK" PRIMARY KEY (id);

-- changeset orhangazibarak:1759733817216-3 splitStatements:false
ALTER TABLE users DROP CONSTRAINT UC_USERSEMAIL_COL;

-- changeset orhangazibarak:1759733817216-4 splitStatements:false
ALTER TABLE users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

