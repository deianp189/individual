package com.example.videojuegos.controller;

import com.example.videojuegos.entity.Favorito;
import com.example.videojuegos.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @PostMapping
    public ResponseEntity<Favorito> agregarAFavoritos(@RequestBody Favorito favorito) {
        Favorito savedFavorito = favoritoRepository.save(favorito);
        return ResponseEntity.ok(savedFavorito);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> obtenerFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<Favorito> favoritos = favoritoRepository.findAllByUsuarioId(usuarioId);
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoritos);
    }
}
