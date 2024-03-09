INSERT INTO users
(username, password, role, allows_data_access)
VALUES
('vsouth', 'admin', 'USER', TRUE),
('admin', 'adm', 'ANALYST', FALSE);

INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(1, 1, null, '2024-03-05 16:37:23', 'Dear Diary, I had a wonderful day.', 'EXCELLENT', 'GOOD', 'BAD'),
(1, 2, null, '2024-03-05 13:59:00', 'Dear Diary, I was wrong.', 'BAD', 'AWFUL', 'NORMAL'),
(2, null, null, '2024-03-06 01:15:15', 'How to use it?', null, null, 'NORMAL');


INSERT INTO public.health_entries (diastolic_blood_pressure, heart_rate, systolic_blood_pressure)
VALUES
(120, 76, 80),
(110, 60, 70);