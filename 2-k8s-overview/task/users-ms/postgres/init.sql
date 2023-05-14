-- Init database for postgres

CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL AUTO_INCREMENT,
    username character varying(255) NOT NULL,
    number_of_posts integer NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_username UNIQUE (username)
);