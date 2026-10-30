
package com.gimnasio.gimnasiomarcosweb.controller;

import com.gimnasio.gimnasiomarcosweb.model.Cliente;
import com.gimnasio.gimnasiomarcosweb.model.Entrenador;
import com.gimnasio.gimnasiomarcosweb.model.Membresia;
import com.gimnasio.gimnasiomarcosweb.model.Reserva;
import com.gimnasio.gimnasiomarcosweb.service.GimnasioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GimnasioController {

    private GimnasioService service;

    public GimnasioController(GimnasioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("clientes", service.cantidadClientes());
        model.addAttribute("membresias", service.cantidadMembresias());
        model.addAttribute("entrenadores", service.cantidadEntrenadores());
        model.addAttribute("reservas", service.cantidadReservas());
        return "index";
    }

    // CLIENTES

@   GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", service.listarClientes());
        model.addAttribute("todosLosClientes", service.listarClientes());
        return "clientes";
    }

    @GetMapping("/clientes/buscar")
    public String buscarCliente(@RequestParam int id, Model model) {
        Cliente cliente = service.buscarCliente(id);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes",
                cliente == null ? new Cliente[0] : new Cliente[]{cliente});
        model.addAttribute("todosLosClientes", service.listarClientes());
        return "clientes";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente,
            RedirectAttributes redirectAttributes) {
        service.agregarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "Cliente registrado con éxito");
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        service.eliminarCliente(id);
        return "redirect:/clientes";
    }

    // MEMBRESIAS

    @GetMapping("/membresias")
    public String membresias(Model model) {
        model.addAttribute("membresia", new Membresia());
        model.addAttribute("membresias", service.listarMembresias());
        return "membresias";
    }

    @PostMapping("/membresias/guardar")
    public String guardarMembresia(@ModelAttribute Membresia membresia,
            RedirectAttributes redirectAttributes) {
        service.agregarMembresia(membresia);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "Membresía registrada con éxito");
        return "redirect:/membresias";
    }

    @GetMapping("/membresias/eliminar/{id}")
    public String eliminarMembresia(@PathVariable int id) {
        service.eliminarMembresia(id);
        return "redirect:/membresias";
    }

    // ENTRENADORES

    @GetMapping("/entrenadores")
    public String entrenadores(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("entrenadores", service.listarEntrenadores());
        return "entrenadores";
    }

    @PostMapping("/entrenadores/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador,
            RedirectAttributes redirectAttributes) {
        service.agregarEntrenador(entrenador);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "Entrenador registrado con éxito");
        return "redirect:/entrenadores";
    }

    @GetMapping("/entrenadores/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable int id) {
        service.eliminarEntrenador(id);
        return "redirect:/entrenadores";
    }

    // RESERVAS

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("reservas", service.listarReservas());
        model.addAttribute("clientesDisponibles", service.listarClientes());
        model.addAttribute("entrenadoresDisponibles", service.listarEntrenadores());
        return "reservas";
    }

    @PostMapping("/reservas/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva,
            RedirectAttributes redirectAttributes) {
        service.agregarReserva(reserva);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "Reserva registrada con éxito");
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable int id) {
        service.eliminarReserva(id);
        return "redirect:/reservas";
    }

    // CONSULTA DTO

    @GetMapping("/reservas-detalle")
    public String reservasDetalle(Model model) {
        model.addAttribute("reservasDetalle", service.listarReservasDetalle());
        return "reservas-detalle";
    }

    // GRAFICOS

    @GetMapping("/graficos")
    public String graficos(Model model) {
        model.addAttribute("clientes", service.cantidadClientes());
        model.addAttribute("membresias", service.cantidadMembresias());
        model.addAttribute("entrenadores", service.cantidadEntrenadores());
        model.addAttribute("reservas", service.cantidadReservas());
        return "graficos";
    }

    // ESTADISTICAS

    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("clientes", service.cantidadClientes());
        model.addAttribute("membresias", service.cantidadMembresias());
        model.addAttribute("entrenadores", service.cantidadEntrenadores());
        model.addAttribute("reservas", service.cantidadReservas());
        return "estadisticas";
    }
}
