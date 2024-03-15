package ru.vsouth.wellbeingdiary.utils.diary;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WeatherTypeIdentifier {

    private final List<String> weatherTypes = List.of(
            "Облачно (половина неба или менее)",
            "Облачно (больше половины неба в течение части периода и половина или менее в остальное время)",
            "Облачно (больше половины неба в течение всего периода)",
            "Песчаная буря, пыльная буря или метель",
            "Туман или ледяной туман или густой туман",
            "Морось",
            "Дождь",
            "Снег, или дождь и снег",
            "Ливень",
            "Гроза с осадками или без них");

    public String getWeatherType(int wmoCode) {
        return weatherTypes.get(wmoCode);
    }
}