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


-- call getTrackByArtist(5);

delete from track where title = 'Teeth' limit 1;

INSERT INTO track (title, duration_sec, audio_key, explicit_flag, album_id, artist_id, audio_url) VALUES
('Teeth', 206, 'teeth', 0, null , 2, 'https://cloudflare.com/teeth.mp3');
 
SET @Lastid = LAST_INSERT_id();

-- DELIMITER //

-- DROP PROCEDURE IF EXISTS put_track_to_smallest_playlist//
-- CREATE PROCEDURE put_track_to_smallest_playlist(IN p_track_id INT)
-- BEGIN
--   DECLARE pid INT;
--   DECLARE pos INT;

--   SELECT p.id INTO pid
--   FROM playlist p
--   LEFT JOIN playlist_item pi ON pi.playlist_id = p.id
--   GROUP BY p.id
--   ORDER BY COUNT(pi.track_id), p.id
--   LIMIT 1;

--   SELECT COALESCE(MAX(position),0)+1 INTO pos
--   FROM playlist_item
--   WHERE playlist_id = pid;

--   INSERT INTO playlist_item (playlist_id, track_id, position, added_at)
--   VALUES (pid, p_track_id, pos, NOW());
-- END//

-- DELIMITER ;

-- -- виклик:
CALL put_track_to_smallest_playlist(@Lastid);




