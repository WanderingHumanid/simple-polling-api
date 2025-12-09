-- Migration Script for Adding createdBy Field to Existing Polls
-- Run this script if you have existing polls in your database

-- This script sets the createdBy field for all existing polls to 'ADMIN'
-- You can modify the value as needed for your use case

USE polldb;

-- Update all existing polls to be owned by ADMIN
-- (Polls created before the feature was added)
UPDATE polls 
SET created_by = 'ADMIN' 
WHERE created_by IS NULL;

-- Verify the update
SELECT id, question, created_by, 
       (SELECT COUNT(*) FROM poll_options WHERE poll_options.poll_id = polls.id) as option_count
FROM polls;

-- Optional: Check which users have voted on which polls
-- This query shows the relationship between users and polls through votes
SELECT p.id as poll_id, 
       p.question, 
       p.created_by, 
       pv.voted_usernames as voters
FROM polls p
LEFT JOIN polls_voted_usernames pv ON p.id = pv.poll_id
ORDER BY p.id;

-- Note: The polls_voted_usernames table is created by Hibernate
-- for the @ElementCollection Set<String> votedUsernames field
