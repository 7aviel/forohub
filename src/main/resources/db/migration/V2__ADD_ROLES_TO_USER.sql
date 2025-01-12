-- Add roles column to the user table
ALTER TABLE user ADD roles VARCHAR(255) NOT NULL DEFAULT 'USER';
