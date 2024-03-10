package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;

public interface WeatherEntryRepository extends JpaRepository<WeatherEntry, Integer> {
}
