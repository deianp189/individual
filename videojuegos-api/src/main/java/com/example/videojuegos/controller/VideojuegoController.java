package com.example.videojuegos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.videojuegos.service.VideojuegoService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RestController
@RequestMapping("/games")
public class VideojuegoController {

    private VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }



@GetMapping("/{id}")
public String obtenerDetalles(@PathVariable String id) {
    return videojuegoService.obtenerDetalles(id);
}


    @GetMapping("/search")
    public String buscarVideojuegos(@RequestParam Optional<String> plataforma,
                                    @RequestParam Optional<String> fechaLanzamiento,
                                    @RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> pageSize,
                                    @RequestParam Optional<Integer> genero) {
        return videojuegoService.buscarVideojuegos(plataforma, fechaLanzamiento, page, pageSize, genero);
    }


}


