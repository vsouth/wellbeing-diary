DROP TABLE IF EXISTS public.users CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id int primary key generated by default as identity,
    username varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(100) not null,
    allows_data_access boolean NOT NULL
);


DROP TABLE IF EXISTS public.weather_entries CASCADE;

CREATE TABLE IF NOT EXISTS public.weather_entries
(
    id SERIAL PRIMARY KEY,
    lat DOUBLE PRECISION,
    lon DOUBLE PRECISION,
    date DATE,
    part_of_day VARCHAR(255),
    temperature DOUBLE PRECISION,
    weather_type VARCHAR(255),
    moon_phase VARCHAR(255)
);

DROP TABLE IF EXISTS public.diary_entries;

CREATE TABLE IF NOT EXISTS public.diary_entries
(
    id int primary key generated by default as identity,
    user_id int not null,
    health_entry_id int,
    weather_entry_id int,
    created_at timestamp without time zone not null,
    entry_text varchar(1000),
    mood varchar(100),
    state_of_health varchar(100),
    activity_amount varchar(100)
);

DROP TABLE IF EXISTS public.health_entries;

CREATE TABLE IF NOT EXISTS public.health_entries
(
    id int primary key generated by default as identity,
    diastolic_blood_pressure integer,
    heart_rate integer,
    systolic_blood_pressure integer
)


