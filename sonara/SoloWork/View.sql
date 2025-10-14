USE music_streaming_platform;

-- CREATE VIEW ExplictTracks AS
-- select id, title, artist_id
-- from track
-- where explicit_flag = 1;

-- SELECT * FROM ExplictTracks;

-- ------------------------------------------------------------------------------------------------

-- drop view if exists BigDuration;
-- CREATE VIEW BigDuration AS
-- select id, title, artist_id
-- from track
-- where duration_sec >= 200;

-- SELECT * FROM BigDuration;

-- ------------------------------------------------------------------------------------------------

-- drop view if exists ExpiredSubscription;
-- CREATE VIEW ExpiredSubscription AS
-- select id, user_id, plan_code, started_at, ends_at
-- from subscription
-- where status >= "expired";

-- SELECT * FROM ExpiredSubscription;

-- ------------------------------------------------------------------------------------------------

