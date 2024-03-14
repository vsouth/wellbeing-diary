package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsouth.wellbeingdiary.model.PartOfDay;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;

import java.util.Date;
import java.util.Optional;

public interface WeatherEntryRepository extends JpaRepository<WeatherEntry, Integer> {
    Optional<WeatherEntry> findByLatAndLonAndDateAndPartOfDay(Double lat, Double lon, Date date, PartOfDay partOfDay);
}
