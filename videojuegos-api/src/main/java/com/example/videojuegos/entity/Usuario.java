package com.example.videojuegos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Favorito> favoritos = new HashSet<>();

    // Default constructor
    public Usuario() {
    }

    // Constructor with parameters
    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Consider using a password encoder here
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // PrePersist and PreUpdate methods
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
               Objects.equals(username, usuario.username) &&
               Objects.equals(email, usuario.email) &&
               Objects.equals(password, usuario.password) &&
               Objects.equals(createdAt, usuario.createdAt) &&
               Objects.equals(updatedAt, usuario.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, createdAt, updatedAt);
    }

    // toString method to display user information
    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", password='******'" +  // Masked for security
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }
}