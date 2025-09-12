-- CREATE DATABASE Music_Streaming_Platform
USE Music_Streaming_Platform;

-- 1) Users
CREATE TABLE IF NOT EXISTS `User` (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255),
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('active','inactive','suspended','deleted','banned') NOT NULL DEFAULT 'active',
  PRIMARY KEY (id),
  UNIQUE KEY uq_user_email (email)
);

-- 2) Artists
CREATE TABLE IF NOT EXISTS Artist (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255),
  start_year INT,
  bio TEXT,
  PRIMARY KEY (id)
) ;

-- 3) Albums
CREATE TABLE IF NOT EXISTS Album (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  release_date DATE,
  cover_url VARCHAR(255),
  artist_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY idx_album_artist (artist_id),
  CONSTRAINT fk_album_artist
    FOREIGN KEY (artist_id) REFERENCES Artist(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- 4) Tracks
CREATE TABLE IF NOT EXISTS Track (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  duration_sec INT NOT NULL,
  audio_url VARCHAR(255),
  explicit_flag BOOLEAN NOT NULL DEFAULT FALSE,
  album_id INT NOT NULL,
  artist_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY idx_track_album (album_id),
  KEY idx_track_artist (artist_id),
  CONSTRAINT fk_track_album
    FOREIGN KEY (album_id) REFERENCES Album(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_track_artist
    FOREIGN KEY (artist_id) REFERENCES Artist(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- 5) Playlists
CREATE TABLE IF NOT EXISTS Playlist (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  is_public BOOLEAN NOT NULL DEFAULT FALSE,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_playlist_user (user_id),
  CONSTRAINT fk_playlist_user
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- 6) Playlist items
CREATE TABLE IF NOT EXISTS PlaylistItem (
  id INT NOT NULL AUTO_INCREMENT,
  playlist_id INT NOT NULL,
  track_id INT NOT NULL,
  position INT NOT NULL,
  added_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_playlist_position (playlist_id, position), -- один трек на позицію в межах плейлиста
  KEY idx_pi_playlist (playlist_id),
  KEY idx_pi_track (track_id),
  CONSTRAINT fk_pi_playlist
    FOREIGN KEY (playlist_id) REFERENCES Playlist(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_pi_track
    FOREIGN KEY (track_id) REFERENCES Track(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- 7) Subscriptions
CREATE TABLE IF NOT EXISTS Subscription (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  plan_code ENUM('free','premium','family','student') NOT NULL DEFAULT 'free',
  started_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ends_at DATETIME NULL,
  status ENUM('trial','active','expired','canceled','pending','suspended') NOT NULL DEFAULT 'trial',
  PRIMARY KEY (id),
  KEY idx_sub_user (user_id),
  CONSTRAINT fk_subscription_user
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- 8) Payments
CREATE TABLE IF NOT EXISTS Payment (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  subscription_id INT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  currency CHAR(3) NOT NULL, -- ISO 4217
  paid_at DATETIME NULL,
  method ENUM('credit_card','paypal','apple_pay','google_pay','crypto')
         NOT NULL DEFAULT 'credit_card',
  status ENUM('pending','completed','failed','declined','refunded')
         NOT NULL DEFAULT 'pending',
  PRIMARY KEY (id),
  KEY idx_payment_user (user_id),
  KEY idx_payment_subscription (subscription_id),
  CONSTRAINT fk_payment_user
    FOREIGN KEY (user_id) REFERENCES `User`(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_payment_subscription
    FOREIGN KEY (subscription_id) REFERENCES Subscription(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ;



