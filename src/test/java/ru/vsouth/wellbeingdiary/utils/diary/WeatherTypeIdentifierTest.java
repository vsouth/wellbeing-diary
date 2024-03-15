package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTypeIdentifierTest {
    private final WeatherTypeIdentifier weatherTypeIdentifier = new WeatherTypeIdentifier();

    @Test
    void getWeatherType() {
        assertEquals("Облачно (половина неба или менее)", weatherTypeIdentifier.getWeatherType(0));
        assertEquals("Облачно (больше половины неба в течение части периода и половина или менее в остальное время)", weatherTypeIdentifier.getWeatherType(1));
        assertEquals("Облачно (больше половины неба в течение всего периода)", weatherTypeIdentifier.getWeatherType(2));
        assertEquals("Песчаная буря, пыльная буря или метель", weatherTypeIdentifier.getWeatherType(3));
        assertEquals("Туман или ледяной туман или густой туман", weatherTypeIdentifier.getWeatherType(4));
        assertEquals("Морось", weatherTypeIdentifier.getWeatherType(5));
        assertEquals("Дождь", weatherTypeIdentifier.getWeatherType(6));
        assertEquals("Снег, или дождь и снег", weatherTypeIdentifier.getWeatherType(7));
        assertEquals("Ливень", weatherTypeIdentifier.getWeatherType(8));
        assertEquals("Гроза с осадками или без них", weatherTypeIdentifier.getWeatherType(9));
    }
}