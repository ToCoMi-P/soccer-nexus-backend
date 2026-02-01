CREATE TABLE match_day_entity (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    price NUMERIC(10, 2),
    match_location VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP
);
