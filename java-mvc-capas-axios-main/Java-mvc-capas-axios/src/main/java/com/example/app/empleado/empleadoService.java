package com.example.app.empleado;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class empleadoService {

    private final empleadoRepository empleadoRepository;

    public List<empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public empleado crear(empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public empleado actualizar(Long id, empleado empleado) {
        empleado existente = obtenerPorId(id);
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setEmail(empleado.getEmail());
        existente.setTelefono(empleado.getTelefono());
        existente.setFechaContratacion(empleado.getFechaContratacion());
        existente.setSalario(empleado.getSalario());
        existente.setCargo(empleado.getCargo());
        existente.setDepartamento(empleado.getDepartamento());
        return empleadoRepository.save(existente);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
