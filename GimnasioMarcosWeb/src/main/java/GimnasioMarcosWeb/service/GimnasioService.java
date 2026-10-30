package com.gimnasio.gimnasiomarcosweb.service;

import com.gimnasio.gimnasiomarcosweb.model.*;
import com.gimnasio.gimnasiomarcosweb.repository.*;
import com.gimnasio.gimnasiomarcosweb.dto.ReservaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GimnasioService {

    private final ClienteRepository clienteRepository;
    private final MembresiaRepository membresiaRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final ReservaRepository reservaRepository;

    public GimnasioService(
            ClienteRepository clienteRepository,
            MembresiaRepository membresiaRepository,
            EntrenadorRepository entrenadorRepository,
            ReservaRepository reservaRepository) {

        this.clienteRepository = clienteRepository;
        this.membresiaRepository = membresiaRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.reservaRepository = reservaRepository;
    }

    // CLIENTES

    public void agregarCliente(Cliente cliente) {
        cliente.setId(0);
        clienteRepository.save(cliente);
    }

    public Cliente[] listarClientes() {
        List<Cliente> lista = clienteRepository.findAll();
        return lista.toArray(new Cliente[0]);
    }

    public Cliente buscarCliente(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public int cantidadClientes() {
        return (int) clienteRepository.count();
    }

    // MEMBRESIAS

    public void agregarMembresia(Membresia membresia) {
        membresia.setId(0);
        membresiaRepository.save(membresia);
    }

    public Membresia[] listarMembresias() {
        List<Membresia> lista = membresiaRepository.findAll();
        return lista.toArray(new Membresia[0]);
    }

    public Membresia buscarMembresia(int id) {
        return membresiaRepository.findById(id).orElse(null);
    }

    public void eliminarMembresia(int id) {
        membresiaRepository.deleteById(id);
    }

    public int cantidadMembresias() {
        return (int) membresiaRepository.count();
    }

    // ENTRENADORES

    public void agregarEntrenador(Entrenador entrenador) {
        entrenador.setId(0);
        entrenadorRepository.save(entrenador);
    }

    public Entrenador[] listarEntrenadores() {
        List<Entrenador> lista = entrenadorRepository.findAll();
        return lista.toArray(new Entrenador[0]);
    }

    public Entrenador buscarEntrenador(int id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    public void eliminarEntrenador(int id) {
        entrenadorRepository.deleteById(id);
    }

    public int cantidadEntrenadores() {
        return (int) entrenadorRepository.count();
    }

    // RESERVAS

    public void agregarReserva(Reserva reserva) {
        reserva.setId(0);
        reservaRepository.save(reserva);
    }

    public Reserva[] listarReservas() {
        List<Reserva> lista = reservaRepository.findAll();
        return lista.toArray(new Reserva[0]);
    }

    public Reserva buscarReserva(int id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminarReserva(int id) {
        reservaRepository.deleteById(id);

        if (reservaRepository.count() == 0) {
            reservaRepository.reiniciarSecuenciaReservas();
        }
    }

    public int cantidadReservas() {
        return (int) reservaRepository.count();
    }

    // CONSULTA DTO DE RESERVAS

    public ReservaDTO[] listarReservasDetalle() {
        List<Object[]> resultados = reservaRepository.obtenerReservasDetalle();

        ReservaDTO[] detalles = new ReservaDTO[resultados.size()];

        for (int i = 0; i < resultados.size(); i++) {
            Object[] fila = resultados.get(i);

            detalles[i] = new ReservaDTO(
                ((Number) fila[0]).intValue(),
                (String) fila[1],
                (String) fila[2],
                (String) fila[3],
                (String) fila[4],
                String.valueOf(fila[5]),
                String.valueOf(fila[6]),
                (String) fila[7]
            );
        }

        return detalles;
    }
}