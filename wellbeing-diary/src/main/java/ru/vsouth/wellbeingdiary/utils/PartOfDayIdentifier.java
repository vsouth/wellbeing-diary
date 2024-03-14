package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.model.PartOfDay;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Component
public class PartOfDayIdentifier {
    public PartOfDay calculatePartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        int hours = localDateTime.getHour();

        if (hours < 6) {
            return PartOfDay.NIGHT;
        } else if (hours < 12) {
            return PartOfDay.MORNING;
        } else if (hours < 18) {
            return PartOfDay.AFTERNOON;
        } else {
            return PartOfDay.EVENING;
        }
    }
}
