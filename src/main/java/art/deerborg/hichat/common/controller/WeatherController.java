package art.deerborg.hichat.common.controller;

import art.deerborg.hichat.common.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String location) {
        return weatherService.getWeather(location);
    }
}
