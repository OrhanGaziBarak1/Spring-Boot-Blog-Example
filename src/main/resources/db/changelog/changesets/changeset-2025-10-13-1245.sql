-- changeset orhangazibarak: add foreign key for article table

ALTER TABLE article
ADD CONSTRAINT fk_article_author
FOREIGN KEY (author_id) REFERENCES users(id);