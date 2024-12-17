-- Drop the database if it already exists
DROP DATABASE IF EXISTS tracker_db;

-- Create the database
CREATE DATABASE tracker_db;

-- Use the newly created database
USE tracker_db;

-- Create the 'items' table
CREATE TABLE items (
                       id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each item
                       name VARCHAR(255) NOT NULL,          -- Name of the item
                       calories INT NOT NULL,               -- Calorie count of the item
                       protein FLOAT NOT NULL,              -- Protein content in grams
                       carbohydrates FLOAT NOT NULL,        -- Carbohydrate content in grams
                       fats FLOAT NOT NULL                  -- Fat content in grams
);

-- Create the 'tracker' table
CREATE TABLE tracker (
                         id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each tracking entry
                         item INT NOT NULL,                   -- Item ID (foreign key)
                         date DATE NOT NULL,                  -- Date of the tracking entry
                         time TIME NOT NULL,                  -- Time of the tracking entry
                         CONSTRAINT fk_item FOREIGN KEY (item) REFERENCES items(id) -- Foreign key constraint
                             ON DELETE CASCADE                 -- Delete tracker entries if the item is deleted
                             ON UPDATE CASCADE                 -- Update tracker entries if the item ID is updated
);
