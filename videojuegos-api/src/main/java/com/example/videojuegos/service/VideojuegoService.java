// VideojuegoService.java
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

    public String buscarVideojuegos(Optional<String> plataforma, Optional<String> fechaLanzamiento,
                                    Optional<Integer> page, Optional<Integer> pageSize, Optional<Integer> genero) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/games")
                .queryParam("key", apiKey);

        plataforma.ifPresent(p -> uriBuilder.queryParam("platforms", p));
        fechaLanzamiento.ifPresent(f -> uriBuilder.queryParam("ordering", f));
        page.ifPresent(p -> uriBuilder.queryParam("page", p));        
        pageSize.ifPresent(ps -> uriBuilder.queryParam("page_size", ps));
        genero.ifPresent(ps -> uriBuilder.queryParam("genres", genero));

        String url = uriBuilder.toUriString();
        System.out.println("############################################");
        System.out.println("Request URL: " + url);  // Imprime la URL en la consola
        System.out.println("############################################");
        return restTemplate.getForObject(url, String.class);
    }
}
