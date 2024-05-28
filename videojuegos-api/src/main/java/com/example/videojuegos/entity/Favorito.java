package com.example.videojuegos.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "juego_id")
    private String juegoId;

    @Column(name = "fecha_agregado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAgregado;

    public Favorito() {
    }

    public Favorito(Usuario usuario, String juegoId, Date fechaAgregado) {
        this.usuario = usuario;
        this.juegoId = juegoId;
        this.fechaAgregado = fechaAgregado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorito)) return false;
        Favorito favorito = (Favorito) o;
        return Objects.equals(getId(), favorito.getId()) &&
                Objects.equals(getUsuario(), favorito.getUsuario()) &&
                Objects.equals(getJuegoId(), favorito.getJuegoId()) &&
                Objects.equals(getFechaAgregado(), favorito.getFechaAgregado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getJuegoId(), getFechaAgregado());
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", juegoId='" + juegoId + '\'' +
                ", fechaAgregado=" + fechaAgregado +
                '}';
    }
}
