INSERT INTO users
(username, password, role, allows_data_access)
VALUES
('vsouth', 'admin', 'USER', TRUE),
('admin', 'adm', 'ANALYST', FALSE);

INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(1, 1, 2, '2024-03-05 16:37:23', 'Dear Diary, I had a wonderful day.', 'EXCELLENT', 'GOOD', 'BAD'),
(1, 2, 1, '2024-03-05 13:59:00', 'Dear Diary, I was wrong.', 'BAD', 'AWFUL', 'NORMAL'),
(2, null, 3, '2024-03-06 01:15:15', 'How to use it?', null, null, 'NORMAL');


INSERT INTO public.health_entries (diastolic_blood_pressure, heart_rate, systolic_blood_pressure)
VALUES
(120, 76, 80),
(110, 60, 70);


INSERT INTO public.weather_entries (lat, lon, date, part_of_day, temperature, weather_type, moon_phase)
VALUES
(40.7128, -74.0060, '2022-01-01', 'MORNING', 20.5, 'Sunny', 'Full Moon'),
(34.0522, -118.2437, '2022-01-02', 'AFTERNOON', 25.3, 'Cloudy', 'New Moon'),
(51.5074, -0.1278, '2022-01-03', 'EVENING', 15.8, 'Rainy', 'First Quarter');