USE music_streaming_platform;

-- DROP PROCEDURE IF EXISTS getTrackByArtist;

-- DELIMITER //

-- CREATE PROCEDURE getTrackByArtist(IN artist_id BIGINT)
-- BEGIN
--     SELECT 
--         t.title, 
--         t.audio_url, 
--         a.name AS artist_name
--     FROM track t
--     LEFT JOIN artist a ON t.artist_id = a.id
--     WHERE t.artist_id = artist_id;
-- END //

-- DELIMITER ;




call getTrackByArtist(5);


