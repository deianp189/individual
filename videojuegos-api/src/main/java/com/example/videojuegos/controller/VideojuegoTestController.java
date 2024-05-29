package com.example.videojuegos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.videojuegos.service.VideojuegoService;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class VideojuegoTestController {

    private VideojuegoService videojuegoService;

    public VideojuegoTestController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }

    @GetMapping("/search")
    public String buscarVideojuegos(@RequestParam Optional<String> plataforma,
                                    @RequestParam Optional<String> fechaLanzamiento,
                                    @RequestParam Optional<Boolean> random,
                                    @RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> pageSize) {
        return videojuegoService.buscarVideojuegos(plataforma, fechaLanzamiento, page, pageSize, random);
    }
}


