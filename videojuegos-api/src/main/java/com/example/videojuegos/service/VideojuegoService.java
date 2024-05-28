package com.example.videojuegos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VideojuegoService {

    private final RestTemplate restTemplate;
    private final String API_BASE_URL = "https://api.rawg.io/api/games";

    
    public VideojuegoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buscarVideojuegos(String plataforma, String genero, String fechaLanzamiento) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(API_BASE_URL)
                .queryParam("platforms", plataforma)
                .queryParam("genres", genero)
                .queryParam("dates", fechaLanzamiento);
        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
