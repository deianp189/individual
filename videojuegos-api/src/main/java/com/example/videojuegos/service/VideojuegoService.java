// VideojuegoService.java
package com.example.videojuegos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;
import java.util.Random;

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

    public String buscarVideojuegos(Optional<String> plataforma, Optional<String> fechaLanzamiento,
                                    Optional<Integer> page, Optional<Integer> pageSize, Optional<Boolean> random) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/games")
                .queryParam("key", apiKey);

        plataforma.ifPresent(p -> uriBuilder.queryParam("platforms", p));
        fechaLanzamiento.ifPresent(f -> uriBuilder.queryParam("ordering", f));
        if (random.isPresent() && random.get()) {
            int randomPage = new Random().nextInt(100) + 1; // Asume que hay 100 páginas, ajustar según necesario
            uriBuilder.queryParam("page", randomPage);
        } else {
            page.ifPresent(p -> uriBuilder.queryParam("page", p));
        }
        pageSize.ifPresent(ps -> uriBuilder.queryParam("page_size", ps));

        String url = uriBuilder.toUriString();
        System.out.println("Request URL: " + url);  // Imprime la URL en la consola
        return restTemplate.getForObject(url, String.class);
    }
}
