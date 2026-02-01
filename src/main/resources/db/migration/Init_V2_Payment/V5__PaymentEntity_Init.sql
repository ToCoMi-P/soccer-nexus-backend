CREATE TABLE payment_entity (
    id BIGSERIAL PRIMARY KEY,
    match_day_id BIGINT,
    player_id BIGINT,
    price NUMERIC(10, 2),
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    reviewed_at TIMESTAMP,
    paid_at TIMESTAMP
);

ALTER TABLE payment_entity
    ADD CONSTRAINT fk_payment_match_day
        FOREIGN KEY (match_day_id)
        REFERENCES match_day_entity (id);

ALTER TABLE payment_entity
    ADD CONSTRAINT fk_payment_player
        FOREIGN KEY (player_id)
        REFERENCES player_entity (id);
