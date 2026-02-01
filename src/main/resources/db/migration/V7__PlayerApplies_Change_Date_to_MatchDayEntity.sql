-- 1. Alte date-Spalte entfernen
ALTER TABLE player_applies 
    DROP COLUMN date;

-- 2. Neue matchday_id-Spalte hinzufügen  
ALTER TABLE player_applies 
    ADD COLUMN matchday_id BIGINT NOT NULL;

-- 3. Foreign Key für matchday hinzufügen
ALTER TABLE player_applies
    ADD CONSTRAINT fk_player_applies_matchday 
        FOREIGN KEY (matchday_id) 
        REFERENCES match_day_entity(id);
