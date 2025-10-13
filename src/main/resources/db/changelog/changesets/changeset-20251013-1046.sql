-- liquibase formatted sql

-- changeset orhangazibarak:1760352419298-9 splitStatements:false
ALTER TABLE article DROP CONSTRAINT fk_article_author;

-- changeset orhangazibarak:1760352419298-7 splitStatements:false
ALTER TABLE users ADD public_id UUID NOT NULL DEFAULT gen_random_uuid();

-- changeset orhangazibarak:1760352419298-8 splitStatements:false
ALTER TABLE users ADD CONSTRAINT UC_USERSPUBLIC_ID_COL UNIQUE (public_id);

-- changeset orhangazibarak:1760352419298-1 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'article' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.article drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1760352419298-2 splitStatements:false
ALTER TABLE article ADD CONSTRAINT "articlePK" PRIMARY KEY (id);

-- changeset orhangazibarak:1760352419298-3 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'users' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.users drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1760352419298-4 splitStatements:false
ALTER TABLE users ADD CONSTRAINT "usersPK" PRIMARY KEY (id);

-- changeset orhangazibarak:1760352419298-5 splitStatements:false
ALTER TABLE users DROP CONSTRAINT UC_USERSEMAIL_COL;

-- changeset orhangazibarak:1760352419298-6 splitStatements:false
ALTER TABLE users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

