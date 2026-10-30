package com.gimnasio.gimnasiomarcosweb.repository;

import com.gimnasio.gimnasiomarcosweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
