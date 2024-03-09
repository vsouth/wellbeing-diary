package ru.vsouth.wellbeingdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsouth.wellbeingdiary.model.HealthEntry;

@Repository
public interface HealthEntryRepository extends JpaRepository<HealthEntry, Integer> {
}
