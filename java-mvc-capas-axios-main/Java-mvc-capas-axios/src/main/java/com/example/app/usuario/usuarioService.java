package com.example.app.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class usuarioService {

    private final usuarioRepository usuarioRepository;

    public boolean validarLogin(String login, String clave) {
        usuario usuario = usuarioRepository.findByLoginAndClave(login, clave);
        return usuario != null;
    }

    public List<usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public usuario crear(usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public usuario actualizar(Long id, usuario usuario) {
        usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existente.setNombre(usuario.getNombre());
        existente.setLogin(usuario.getLogin());
        existente.setClave(usuario.getClave());
        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
