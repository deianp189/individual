package com.example.videojuegos.controller;

import com.example.videojuegos.entity.Usuario;
import com.example.videojuegos.entity.Favorito;
import com.example.videojuegos.repository.FavoritoRepository;
import com.example.videojuegos.dto.FavoritoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @PostMapping
public ResponseEntity<FavoritoDTO> agregarAFavoritos(@RequestBody FavoritoDTO favoritoDTO) {
    Favorito favorito = new Favorito();
    favorito.setUsuario(new Usuario(favoritoDTO.getUsuarioId()));  // Ahora esto debería funcionar correctamente.
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

    private FavoritoDTO convertirADTO(Favorito favorito) {
        FavoritoDTO dto = new FavoritoDTO();
        dto.setId(favorito.getId());
        dto.setUsuarioId(favorito.getUsuario().getId());
        dto.setJuegoId(favorito.getJuegoId());
        return dto;
    }
}
