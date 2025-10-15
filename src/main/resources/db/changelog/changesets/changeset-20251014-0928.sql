-- liquibase formatted sql

-- changeset orhangazibarak:1760434109679-13 splitStatements:false
ALTER TABLE follows DROP CONSTRAINT fk_followed_user_public_id;

-- changeset orhangazibarak:1760434109679-14 splitStatements:false
ALTER TABLE follows DROP CONSTRAINT fk_user_public_id;

-- changeset orhangazibarak:1760434109679-1 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'article' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.article drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1760434109679-2 splitStatements:false
ALTER TABLE article ADD CONSTRAINT "articlePK" PRIMARY KEY (id);

-- changeset orhangazibarak:1760434109679-3 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'follows' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.follows drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1760434109679-4 splitStatements:false
ALTER TABLE follows ADD CONSTRAINT "followsPK" PRIMARY KEY (id);

-- changeset orhangazibarak:1760434109679-5 splitStatements:false
DO $$ DECLARE constraint_name varchar;
BEGIN
  SELECT tc.CONSTRAINT_NAME into strict constraint_name
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
    WHERE CONSTRAINT_TYPE = 'PRIMARY KEY'
      AND TABLE_NAME = 'users' AND TABLE_SCHEMA = 'public';
    EXECUTE 'alter table public.users drop constraint "' || constraint_name || '"';
END $$;

-- changeset orhangazibarak:1760434109679-6 splitStatements:false
ALTER TABLE users ADD CONSTRAINT "usersPK" PRIMARY KEY (id);

-- changeset orhangazibarak:1760434109679-7 splitStatements:false
ALTER TABLE follows DROP CONSTRAINT unique_user_follow;

-- changeset orhangazibarak:1760434109679-8 splitStatements:false
ALTER TABLE follows ADD CONSTRAINT unique_user_follow UNIQUE (user_public_id, followed_user_public_id);

-- changeset orhangazibarak:1760434109679-9 splitStatements:false
ALTER TABLE users DROP CONSTRAINT UC_USERSPUBLIC_ID_COL;

-- changeset orhangazibarak:1760434109679-10 splitStatements:false
ALTER TABLE users ADD CONSTRAINT UC_USERSPUBLIC_ID_COL UNIQUE (public_id);

-- changeset orhangazibarak:1760434109679-11 splitStatements:false
ALTER TABLE users DROP CONSTRAINT UC_USERSEMAIL_COL;

-- changeset orhangazibarak:1760434109679-12 splitStatements:false
ALTER TABLE users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

