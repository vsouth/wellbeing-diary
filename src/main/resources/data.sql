INSERT INTO users
(username, password, role, allows_data_access)
VALUES
('vsouth', '$2a$10$BU5bQX1hFBi2.fUsrEINf.NwBZ2W6cfOAq1xIcaDd0wE84/XCGBDS', 'USER', TRUE),
('admin', '$2a$10$M0cpmsJy6DwzMUwsTkLncO7BNt7gL9skBg.ArpdsQUO6D3SblVOnm', 'ANALYST', FALSE);

INSERT INTO diary_entries
(user_id, health_entry_id, weather_entry_id, created_at, entry_text, mood, state_of_health, activity_amount)
VALUES
(1, 1, 2, '2024-03-05 16:37:23', 'Сегодня был хороший день. Я всем доволен. Все отлично.', 'EXCELLENT', 'GOOD', 'BAD'),
(1, 2, 1, '2024-03-05 13:59:00', 'Показалось.', 'BAD', 'AWFUL', 'NORMAL'),
(2, null, 3, '2024-03-06 01:15:15', 'Что тут писать?', 'NORMAL', null, 'NORMAL');


INSERT INTO public.health_entries (diastolic_blood_pressure, systolic_blood_pressure, heart_rate)
VALUES
(80, 120, 76),
(70, 100, 60);


INSERT INTO public.weather_entries (lat, lon, date, part_of_day, temperature, weather_type)
VALUES
(40.7128, -74.0060, '2022-01-01', 'MORNING', 20.5, 'Ясно'),
(34.0522, -118.2437, '2022-01-02', 'AFTERNOON', 25.3, 'Облачно'),
(51.5074, -0.1278, '2022-01-03', 'EVENING', 15.8, 'Дождь');