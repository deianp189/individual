package com.example.videojuegos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.videojuegos.service.VideojuegoService;

@RestController
@RequestMapping("/test")
public class VideojuegoTestController {

    private VideojuegoService videojuegoService;
    public VideojuegoTestController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }

    @GetMapping("/buscarVideojuegos")
    public String buscarVideojuegos() {
        // Modifica estos parámetros según necesites para probar diferentes respuestas
        return videojuegoService.buscarVideojuegos("18", "4", "2020-01-01,2020-12-31");
    }
}
