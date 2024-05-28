package com.example.videojuegos.repository;

import com.example.videojuegos.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findAllByUsuarioId(Long usuarioId);
}
