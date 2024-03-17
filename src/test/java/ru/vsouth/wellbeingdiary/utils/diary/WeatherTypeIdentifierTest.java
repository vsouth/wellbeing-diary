package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTypeIdentifierTest {
    private final WeatherTypeIdentifier weatherTypeIdentifier = new WeatherTypeIdentifier();

    @Test
    void testGetWeatherType_ValidCode() {
        assertEquals("Ясно", weatherTypeIdentifier.getWeatherType(0));
        assertEquals("Частично облачно", weatherTypeIdentifier.getWeatherType(1));
        assertEquals("Облачно", weatherTypeIdentifier.getWeatherType(3));
        assertEquals("Дым", weatherTypeIdentifier.getWeatherType(4));
        assertEquals("Мгла", weatherTypeIdentifier.getWeatherType(5));
        assertEquals("Пыль", weatherTypeIdentifier.getWeatherType(6));
        assertEquals("Туман", weatherTypeIdentifier.getWeatherType(10));
        assertEquals("Гроза", weatherTypeIdentifier.getWeatherType(13));
        assertEquals("Дождь", weatherTypeIdentifier.getWeatherType(14));
        assertEquals("Снег", weatherTypeIdentifier.getWeatherType(22));
        assertEquals("Ливни", weatherTypeIdentifier.getWeatherType(27));
        assertEquals("Песчаная буря", weatherTypeIdentifier.getWeatherType(31));
    }

    @Test
    void testGetWeatherType_InvalidCode() {
        assertEquals("Неизвестный тип погоды", weatherTypeIdentifier.getWeatherType(100));
        assertEquals("Неизвестный тип погоды", weatherTypeIdentifier.getWeatherType(-1));
    }
}