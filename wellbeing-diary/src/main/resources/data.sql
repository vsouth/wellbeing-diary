INSERT INTO users
(username, password, role, allows_data_access)
VALUES
('vsouth', 'admin', 'USER', TRUE);


INSERT INTO users
(username, password, role, allows_data_access)
VALUES
('admin', 'adm', 'ANALYST', FALSE);


INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(1, null, null, '2024-03-05 16:37:23', 'Dear Diary, I had a wonderful day.', 'EXCELLENT', 'GOOD', 'BAD');

INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(1, null, null, '2024-03-05 13:59:00', 'Dear Diary, I was wrong.', 'BAD', 'AWFUL', 'NORMAL');

INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(2, null, null, '2024-03-06 01:15:15', 'How to use it?', null, null, 'NORMAL');