package com.example.videojuegos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;

@Service
public class VideojuegoService {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;
    private final String apiKey;

    public VideojuegoService(RestTemplate restTemplate, @Value("${api.external.url}") String apiBaseUrl,
                             @Value("${api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiBaseUrl = apiBaseUrl;
        this.apiKey = apiKey;
    }

    public String buscarVideojuegos(Optional<String> plataforma, Optional<String> genero, Optional<String> fechaLanzamiento,
                                    Optional<Integer> page, Optional<Integer> pageSize) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/games")
                .queryParam("key", apiKey)
                .queryParam("platforms", plataforma.orElse(null))
                .queryParam("genres", genero.orElse(null))
                .queryParam("dates", fechaLanzamiento.orElse(null))
                .queryParam("page", page.orElse(null))
                .queryParam("page_size", pageSize.orElse(null));

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
