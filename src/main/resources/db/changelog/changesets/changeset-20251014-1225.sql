ALTER TABLE article
ADD CONSTRAINT fk_user_public_id
FOREIGN KEY (user_public_id) REFERENCES users(public_id);