package com.example.app.empleado;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class empleadoController {

    private final empleadoService empleadoService;

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<empleado>> obtenerTodos() {
        return ResponseEntity.ok(empleadoService.obtenerTodos());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<empleado> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.obtenerPorId(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<empleado> crear(@RequestBody empleado empleado) {
        return ResponseEntity.ok(empleadoService.crear(empleado));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<empleado> actualizar(@PathVariable Long id, @RequestBody empleado empleado) {
        return ResponseEntity.ok(empleadoService.actualizar(id, empleado));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
