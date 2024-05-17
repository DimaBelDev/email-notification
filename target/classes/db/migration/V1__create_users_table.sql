CREATE TABLE t_user(
                      id SERIAL PRIMARY KEY,
                      firstname VARCHAR(255),
                      lastname VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR NOT NULL
);