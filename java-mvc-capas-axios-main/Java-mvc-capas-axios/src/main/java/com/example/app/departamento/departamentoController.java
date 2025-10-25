package com.example.app.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class departamentoController {

    private final departamentoService departamentoService;

    //Obtener todos los departamentos
    @GetMapping
    public ResponseEntity<List<departamento>> obtenerTodos() {
        return ResponseEntity.ok(departamentoService.obtenerTodos());
    }

    //Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<departamento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.obtenerPorId(id));
    }

    //Crear
    @PostMapping
    public ResponseEntity<departamento> crear(@RequestBody departamento departamento) {
        return ResponseEntity.ok(departamentoService.crear(departamento));
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<departamento> actualizar(@PathVariable Long id, @RequestBody departamento departamento) {
        return ResponseEntity.ok(departamentoService.actualizar(id, departamento));
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
