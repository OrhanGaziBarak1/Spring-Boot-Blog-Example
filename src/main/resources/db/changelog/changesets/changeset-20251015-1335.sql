ALTER TABLE favorites
ADD CONSTRAINT fk_user_public_id
FOREIGN KEY (user_public_id) REFERENCES users(public_id);

ALTER TABLE favorites
ADD CONSTRAINT fk_article_id
FOREIGN KEY (article_id) REFERENCES article(id);