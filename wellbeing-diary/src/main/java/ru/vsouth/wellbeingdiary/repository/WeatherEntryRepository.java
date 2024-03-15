package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;

import java.util.Date;
import java.util.Optional;

public interface WeatherEntryRepository extends JpaRepository<WeatherEntry, Integer> {
    Optional<WeatherEntry> findByLatAndLonAndDateAndPartOfDay(Double lat, Double lon, Date date, PartOfDay partOfDay);
}
