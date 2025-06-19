-- liquibase formatted sql

-- changeset denismalinin:1750342427453-1
CREATE SEQUENCE  IF NOT EXISTS game_result_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1750342427453-2
CREATE SEQUENCE  IF NOT EXISTS game_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1750342427453-3
CREATE TABLE game (id BIGINT NOT NULL, state JSONB, created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, CONSTRAINT pk_game PRIMARY KEY (id));

-- changeset denismalinin:1750342427453-4
CREATE TABLE game_result (id BIGINT NOT NULL, winner_id UUID NOT NULL, started_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, end_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, CONSTRAINT pk_game_result PRIMARY KEY (id));

-- changeset denismalinin:1750342427453-5
CREATE TABLE game_result_user (game_result_id BIGINT NOT NULL, user_id UUID NOT NULL, CONSTRAINT pk_game_result_user PRIMARY KEY (game_result_id, user_id));

-- changeset denismalinin:1750342427453-6
CREATE TABLE game_user (game_id BIGINT NOT NULL, user_id UUID NOT NULL, CONSTRAINT pk_game_user PRIMARY KEY (game_id, user_id));

-- changeset denismalinin:1750342427453-7
CREATE TABLE revoked_token (token VARCHAR(255) NOT NULL, expired date NOT NULL, CONSTRAINT pk_revoked_token PRIMARY KEY (token));

-- changeset denismalinin:1750342427453-8
CREATE TABLE room (code VARCHAR(255) NOT NULL, host_id UUID NOT NULL, max_size INTEGER NOT NULL, is_public BOOLEAN NOT NULL, created_at TIMESTAMP WITHOUT TIME ZONE, CONSTRAINT pk_room PRIMARY KEY (code));

-- changeset denismalinin:1750342427453-9
CREATE TABLE room_user (room_id VARCHAR(255) NOT NULL, user_id UUID NOT NULL);

-- changeset denismalinin:1750342427453-10
CREATE TABLE users (id UUID NOT NULL, telegram_id BIGINT, created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, name VARCHAR(255) NOT NULL, avatar VARCHAR(255), is_bot BOOLEAN NOT NULL, win INTEGER NOT NULL, loss INTEGER NOT NULL, CONSTRAINT pk_users PRIMARY KEY (id));

-- changeset denismalinin:1750342427453-11
ALTER TABLE game_result ADD CONSTRAINT FK_GAME_RESULT_ON_WINNER FOREIGN KEY (winner_id) REFERENCES users (id);

-- changeset denismalinin:1750342427453-12
ALTER TABLE room ADD CONSTRAINT FK_ROOM_ON_HOST FOREIGN KEY (host_id) REFERENCES users (id);

-- changeset denismalinin:1750342427453-13
ALTER TABLE game_user ADD CONSTRAINT fk_game_user_on_game_entity FOREIGN KEY (game_id) REFERENCES game (id);

-- changeset denismalinin:1750342427453-14
ALTER TABLE game_user ADD CONSTRAINT fk_game_user_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset denismalinin:1750342427453-15
ALTER TABLE game_result_user ADD CONSTRAINT fk_gamresuse_on_game_result_entity FOREIGN KEY (game_result_id) REFERENCES game_result (id);

-- changeset denismalinin:1750342427453-16
ALTER TABLE game_result_user ADD CONSTRAINT fk_gamresuse_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset denismalinin:1750342427453-17
ALTER TABLE room_user ADD CONSTRAINT fk_room_user_on_room_entity FOREIGN KEY (room_id) REFERENCES room (code);

-- changeset denismalinin:1750342427453-18
ALTER TABLE room_user ADD CONSTRAINT fk_room_user_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset denismalinin:1750342427453-19
INSERT INTO users (id, telegram_id, created_at, name, avatar, is_bot, win, loss) VALUES
('00000000-0000-0000-0000-000000000001', NULL, CURRENT_TIMESTAMP, 'Доктор Ливси', 'https://i.postimg.cc/FH3Rg7CH/temp-Image-C9f-Gq-A.avif', true, 0, 0);

