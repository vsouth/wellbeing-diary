package ru.vsouth.wellbeingdiary.utils.diary;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Наименования типов погоды для сайтов взяты с:
 * https://worldweather.wmo.int/schema/Annex1-BulletinFormat-v1.0.pdf
 * HashMap используется из-за того, что некоторые типы погоды не используются (19)
 */
@Component
public class WeatherTypeIdentifier {
    private final Map<Integer, String> weatherTypes = new HashMap<>();

    public WeatherTypeIdentifier() {
        weatherTypes.put(0, "Ясно");
        weatherTypes.put(1, "Переменная облачность");
        weatherTypes.put(2, "Облачно");
        weatherTypes.put(3, "Облачно");
        weatherTypes.put(4, "Дым");
        weatherTypes.put(5, "Мгла");
        weatherTypes.put(6, "Пыль");
        weatherTypes.put(7, "Пыль");
        weatherTypes.put(8, "Пыль");
        weatherTypes.put(9, "Пыльная буря");
        weatherTypes.put(10, "Туман");
        weatherTypes.put(11, "Туман");
        weatherTypes.put(12, "Туман");
        weatherTypes.put(13, "Гроза");
        weatherTypes.put(14, "Дождь");
        weatherTypes.put(15, "Дождь");
        weatherTypes.put(16, "Дождь");
        weatherTypes.put(17, "Гром");
        weatherTypes.put(18, "Шквалы");
        weatherTypes.put(20, "Морось");
        weatherTypes.put(21, "Дождь");
        weatherTypes.put(22, "Снег");
        weatherTypes.put(23, "Дождь");
        weatherTypes.put(24, "Морось");
        weatherTypes.put(25, "Дождь");
        weatherTypes.put(26, "Снег");
        weatherTypes.put(27, "Ливни");
        weatherTypes.put(28, "Туман");
        weatherTypes.put(29, "Гроза");
        weatherTypes.put(30, "Пыльная буря");
        weatherTypes.put(31, "Песчаная буря");
        weatherTypes.put(32, "Пыльная буря");
        weatherTypes.put(33, "Сильная пыльная буря");
        weatherTypes.put(34, "Сильная песчаная буря");
        weatherTypes.put(35, "Сильная пыльная буря");
        weatherTypes.put(36, "Умеренный снег");
        weatherTypes.put(37, "Сильный снег");
        weatherTypes.put(38, "Умеренный снег");
        weatherTypes.put(39, "Сильный снег");
        weatherTypes.put(40, "Туман");
        weatherTypes.put(41, "Туман");
        weatherTypes.put(42, "Туман");
        weatherTypes.put(43, "Туман");
        weatherTypes.put(44, "Туман");
        weatherTypes.put(45, "Туман");
        weatherTypes.put(46, "Туман");
        weatherTypes.put(47, "Туман");
        weatherTypes.put(48, "Туман");
        weatherTypes.put(49, "Туман");
        weatherTypes.put(50, "Легкая морось");
        weatherTypes.put(51, "Легкая морось");
        weatherTypes.put(52, "Умеренная морось");
        weatherTypes.put(53, "Умеренная морось");
        weatherTypes.put(54, "Плотная морось");
        weatherTypes.put(55, "Плотная морось");
        weatherTypes.put(56, "Легкая морось");
        weatherTypes.put(57, "Плотная морось");
        weatherTypes.put(58, "Легкий дождь");
        weatherTypes.put(59, "Умеренный дождь");
        weatherTypes.put(60, "Легкий дождь");
        weatherTypes.put(61, "Легкий дождь");
        weatherTypes.put(62, "Умеренный дождь");
        weatherTypes.put(63, "Умеренный дождь");
        weatherTypes.put(64, "Сильный дождь");
        weatherTypes.put(65, "Сильный дождь");
        weatherTypes.put(66, "Легкий дождь");
        weatherTypes.put(67, "Сильный дождь");
        weatherTypes.put(68, "Легкий дождь");
        weatherTypes.put(69, "Сильный дождь");
        weatherTypes.put(70, "Легкий снег");
        weatherTypes.put(71, "Легкий снег");
        weatherTypes.put(72, "Умеренный снег");
        weatherTypes.put(73, "Умеренный снег");
        weatherTypes.put(74, "Сильный снег");
        weatherTypes.put(75, "Сильный снег");
        weatherTypes.put(76, "Пыль");
        weatherTypes.put(77, "Снег");
        weatherTypes.put(78, "Снег");
        weatherTypes.put(79, "Снег");
        weatherTypes.put(80, "Легкие ливни");
        weatherTypes.put(81, "Ливни");
        weatherTypes.put(82, "Сильные ливни");
        weatherTypes.put(83, "Легкие ливни");
        weatherTypes.put(84, "Ливни");
        weatherTypes.put(85, "Легкий снег");
        weatherTypes.put(86, "Сильный снег");
        weatherTypes.put(87, "Легкий снег");
        weatherTypes.put(88, "Град");
        weatherTypes.put(89, "Град");
        weatherTypes.put(90, "Град");
        weatherTypes.put(91, "Легкий дождь");
        weatherTypes.put(92, "Сильный дождь");
        weatherTypes.put(93, "Легкий снег");
        weatherTypes.put(94, "Сильный снег");
        weatherTypes.put(95, "Гроза");
        weatherTypes.put(96, "Гроза");
        weatherTypes.put(97, "Сильная гроза");
        weatherTypes.put(98, "Гроза");
        weatherTypes.put(99, "Сильная гроза");
    }

        public String getWeatherType(int wmoCode) {
            return weatherTypes.getOrDefault(wmoCode, "Неизвестный тип погоды");
        }
}