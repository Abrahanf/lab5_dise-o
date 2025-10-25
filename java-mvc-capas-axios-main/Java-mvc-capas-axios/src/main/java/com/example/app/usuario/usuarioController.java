package com.example.app.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class usuarioController {

    private final usuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody usuarioLoginDTO loginDTO) {
        boolean valido = usuarioService.validarLogin(loginDTO.getLogin(), loginDTO.getClave());
        if (valido) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }
    }

    //CRUD Usuarios (para panel de administración)
    @GetMapping
    public ResponseEntity<List<usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<usuario> crear(@RequestBody usuario usuario) {
        return ResponseEntity.ok(usuarioService.crear(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<usuario> actualizar(@PathVariable Long id, @RequestBody usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
