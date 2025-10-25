package com.example.app.empleado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface empleadoRepository extends JpaRepository<empleado, Long> {
}
