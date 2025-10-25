package com.example.app.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<usuario, Long> {
    usuario findByLoginAndClave(String login, String clave);
}
