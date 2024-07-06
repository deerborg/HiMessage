package art.deerborg.hichat.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather(String location) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", location, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
