ALTER TABLE follows
ADD CONSTRAINT fk_user_public_id
FOREIGN KEY (user_public_id) REFERENCES users(public_id);

ALTER TABLE follows
ADD CONSTRAINT fk_followed_user_public_id
FOREIGN KEY (followed_user_public_id) REFERENCES users(public_id);