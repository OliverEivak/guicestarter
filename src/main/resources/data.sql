INSERT INTO user (id, username, password, email, role) VALUES (1, 'user', X'243261243130246a6b4b722e7364356e705565706557544d304f367a4f434a4e716575474f3565674b564c5476436830567a685531464c6e526b6a4f', 'user@asd.asd', 'USER');
INSERT INTO user (id, username, password, email, role) VALUES (2, 'admin', X'243261243130246a6b4b722e7364356e705565706557544d304f367a4f434a4e716575474f3565674b564c5476436830567a685531464c6e526b6a4f', 'admin@asd.asd', 'ADMIN');

INSERT INTO book (title, author, user) VALUES ('The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams', 1);
INSERT INTO book (title, author, user) VALUES ('The Admin Handbook', 'Jeff Jefferson', 2);
