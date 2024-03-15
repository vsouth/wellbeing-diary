package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PartOfDayIdentifierTest {
    @Test
    void testCalculatePartOfDayNight() {
        PartOfDayIdentifier partOfDayIdentifier = new PartOfDayIdentifier();
        Date nightDate = Date.from(LocalDateTime.of(2024, 3, 15, 2, 0).atZone(ZoneId.of("UTC")).toInstant());
        assertEquals(PartOfDay.NIGHT, partOfDayIdentifier.calculatePartOfDay(nightDate));
    }

    @Test
    void testCalculatePartOfDayMorning() {
        PartOfDayIdentifier partOfDayIdentifier = new PartOfDayIdentifier();
        Date morningDate = Date.from(LocalDateTime.of(2024, 3, 15, 8, 0).atZone(ZoneId.of("UTC")).toInstant());
        assertEquals(PartOfDay.MORNING, partOfDayIdentifier.calculatePartOfDay(morningDate));
    }

    @Test
    void testCalculatePartOfDayAfternoon() {
        PartOfDayIdentifier partOfDayIdentifier = new PartOfDayIdentifier();
        Date afternoonDate = Date.from(LocalDateTime.of(2024, 3, 15, 14, 0).atZone(ZoneId.of("UTC")).toInstant());
        assertEquals(PartOfDay.AFTERNOON, partOfDayIdentifier.calculatePartOfDay(afternoonDate));
    }

    @Test
    void testCalculatePartOfDayEvening() {
        PartOfDayIdentifier partOfDayIdentifier = new PartOfDayIdentifier();
        Date eveningDate = Date.from(LocalDateTime.of(2024, 3, 15, 20, 0).atZone(ZoneId.of("UTC")).toInstant());
        assertEquals(PartOfDay.EVENING, partOfDayIdentifier.calculatePartOfDay(eveningDate));
    }
}