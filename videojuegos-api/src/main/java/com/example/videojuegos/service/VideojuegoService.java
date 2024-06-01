// VideojuegoService.java
package com.example.videojuegos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

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

    public String obtenerDetalles(String id) {
        String url = apiBaseUrl + "/games/" + id + "?key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

public List<String> obtenerNoticias() {
        List<String> noticias = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://vandal.elespanol.com/noticias/videojuegos").get();
            Elements elementosNoticias = doc.select(".caja620 a"); // Asumiendo que esta es la selección correcta

            for (int i = 0; i < 3 && i < elementosNoticias.size(); i++) {
                Element noticia = elementosNoticias.get(i);
                String titulo = noticia.select(".titulocaja").text();
                String url = noticia.attr("href");
                String descripcion = noticia.select(".desccaja").text();
                noticias.add(titulo + " - " + descripcion + " Más detalles: " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noticias;
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
        System.out.println("Request URL: " + url); // Imprime la URL en la consola
        System.out.println("############################################");
        return restTemplate.getForObject(url, String.class);
    }

}
