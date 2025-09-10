-- Example SQL fixtures
-- Validate user record presence
SELECT id, email, status FROM users WHERE email = 'qa.user@example.com';

-- Insert a test item (use with caution in non-prod)
-- INSERT INTO items (name, type, created_by) VALUES ('Playwright Item', 'test', 'automation');

-- Cleanup created item
-- DELETE FROM items WHERE name = 'Playwright Item';
