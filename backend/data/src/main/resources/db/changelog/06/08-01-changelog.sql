-- liquibase formatted sql

-- changeset denismalinin:1749405592000-1
CREATE SEQUENCE  IF NOT EXISTS game_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1749405592000-2
CREATE SEQUENCE  IF NOT EXISTS statistic_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1749405592000-3
CREATE TABLE game (id BIGINT NOT NULL, status VARCHAR(255) NOT NULL, state JSONB, started_at TIMESTAMP WITHOUT TIME ZONE, created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, end_at TIMESTAMP WITHOUT TIME ZONE, CONSTRAINT pk_game PRIMARY KEY (id));

-- changeset denismalinin:1749405592000-4
CREATE TABLE game_link (code VARCHAR(255) NOT NULL, game_id BIGINT NOT NULL, CONSTRAINT pk_game_link PRIMARY KEY (code));

-- changeset denismalinin:1749405592000-5
CREATE TABLE game_user (game_id BIGINT NOT NULL, user_id UUID NOT NULL, CONSTRAINT pk_game_user PRIMARY KEY (game_id, user_id));

-- changeset denismalinin:1749405592000-6
CREATE TABLE users (id UUID NOT NULL, telegram_id BIGINT NOT NULL, created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, name VARCHAR(255) NOT NULL, avatar VARCHAR(255), is_bot BOOLEAN NOT NULL, win INTEGER NOT NULL, loss INTEGER NOT NULL, CONSTRAINT pk_users PRIMARY KEY (id));

-- changeset denismalinin:1749405592000-7
ALTER TABLE game_link ADD CONSTRAINT uc_game_link_game UNIQUE (game_id);

-- changeset denismalinin:1749405592000-8
ALTER TABLE users ADD CONSTRAINT uc_users_telegram UNIQUE (telegram_id);

-- changeset denismalinin:1749405592000-9
ALTER TABLE game_link ADD CONSTRAINT FK_GAME_LINK_ON_GAME FOREIGN KEY (game_id) REFERENCES game (id);

-- changeset denismalinin:1749405592000-10
ALTER TABLE game_user ADD CONSTRAINT fk_game_user_on_game_entity FOREIGN KEY (game_id) REFERENCES game (id);

-- changeset denismalinin:1749405592000-11
ALTER TABLE game_user ADD CONSTRAINT fk_game_user_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);

