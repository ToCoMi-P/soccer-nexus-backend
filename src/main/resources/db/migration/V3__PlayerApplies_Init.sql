CREATE TABLE player_applies (
    id BIGSERIAL PRIMARY KEY,
    player_id BIGINT NOT NULL,
    instant VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT fk_player_applies_player FOREIGN KEY (player_id) REFERENCES player_entity(id)
);
