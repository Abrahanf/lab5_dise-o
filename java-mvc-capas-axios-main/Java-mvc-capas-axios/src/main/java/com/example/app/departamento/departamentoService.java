package com.example.app.departamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class departamentoService {

    private final departamentoRepository departamentoRepository;

    public List<departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    public departamento obtenerPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
    }

    public departamento crear(departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public departamento actualizar(Long id, departamento departamento) {
        departamento existente = obtenerPorId(id);
        existente.setNombre(departamento.getNombre());
        existente.setUbicacion(departamento.getUbicacion());
        existente.setPresupuesto(departamento.getPresupuesto());
        return departamentoRepository.save(existente);
    }

    public void eliminar(Long id) {
        departamentoRepository.deleteById(id);
    }
}
