package com.example.videojuegos.controller;

import com.example.videojuegos.entity.Favorito;
import com.example.videojuegos.entity.Usuario;
import com.example.videojuegos.repository.FavoritoRepository;
import com.example.videojuegos.dto.FavoritoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @PostMapping
    public ResponseEntity<?> agregarAFavoritos(@RequestBody FavoritoDTO favoritoDTO) {
        List<Favorito> existentes = favoritoRepository.findAllByUsuarioIdAndJuegoId(favoritoDTO.getUsuarioId(), favoritoDTO.getJuegoId());
        if (!existentes.isEmpty()) {
            return ResponseEntity.status(409).body("El videojuego ya está en tus favoritos.");
        }
        Favorito favorito = new Favorito();
        favorito.setUsuario(new Usuario(favoritoDTO.getUsuarioId()));
        favorito.setJuegoId(favoritoDTO.getJuegoId());
        Favorito savedFavorito = favoritoRepository.save(favorito);
        return ResponseEntity.ok(convertirADTO(savedFavorito));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoDTO>> obtenerFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<Favorito> favoritos = favoritoRepository.findAllByUsuarioId(usuarioId);
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoritos.stream().map(this::convertirADTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/usuario/{usuarioId}/juego/{juegoId}")
public ResponseEntity<?> eliminarFavorito(@PathVariable Long usuarioId, @PathVariable String juegoId) {
    List<Favorito> favoritos = favoritoRepository.findAllByUsuarioIdAndJuegoId(usuarioId, juegoId);
    if (favoritos.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorito no encontrado para el usuario y juego especificados.");
    }
    favoritoRepository.deleteAll(favoritos);
    return ResponseEntity.ok().build();
}


    private FavoritoDTO convertirADTO(Favorito favorito) {
        FavoritoDTO dto = new FavoritoDTO();
        dto.setId(favorito.getId());
        dto.setUsuarioId(favorito.getUsuario().getId());
        dto.setJuegoId(favorito.getJuegoId());
        return dto;
    }
}
