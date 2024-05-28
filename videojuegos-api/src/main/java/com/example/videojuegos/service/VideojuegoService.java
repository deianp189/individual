package com.example.videojuegos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VideojuegoService {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;
    private final String apiKey;

    public VideojuegoService(RestTemplate restTemplate,
                             @Value("${api.external.url}") String apiBaseUrl,
                             @Value("${api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiBaseUrl = apiBaseUrl;
        this.apiKey = apiKey;
    }

    public String buscarVideojuegos(String plataforma, String genero, String fechaLanzamiento) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/games")
                .queryParam("key", apiKey)
                .queryParam("platforms", plataforma)
                .queryParam("genres", genero)
                .queryParam("dates", fechaLanzamiento);
        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
