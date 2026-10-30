package com.gimnasio.gimnasiomarcosweb.repository;

import com.gimnasio.gimnasiomarcosweb.model.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = """
        SELECT
            r.id,
            r.cliente,
            c.dni,
            r.entrenador,
            e.especialidad,
            r.fecha,
            r.hora,
            r.estado
        FROM reserva r
        LEFT JOIN cliente c
            ON LOWER(CONCAT(c.nombre, ' ', c.apellido)) = LOWER(r.cliente)
        LEFT JOIN entrenador e
            ON LOWER(e.nombre) = LOWER(r.entrenador)
        """, nativeQuery = true)
    List<Object[]> obtenerReservasDetalle();

    @Query(value = """
        SELECT setval(
            pg_get_serial_sequence('reserva', 'id'),
            1,
            false
        )
        """, nativeQuery = true)
    Long reiniciarSecuenciaReservas();
}