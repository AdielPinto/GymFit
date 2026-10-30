package com.gimnasio.gimnasiomarcosweb.dto;

public class ReservaDTO {

    private Integer id;
    private String cliente;
    private String dni;
    private String entrenador;
    private String especialidad;
    private String fecha;
    private String hora;
    private String estado;

    public ReservaDTO(Integer id, String cliente, String dni,
            String entrenador, String especialidad,
            String fecha, String hora, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.dni = dni;
        this.entrenador = entrenador;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public String getCliente() { return cliente; }
    public String getDni() { return dni; }
    public String getEntrenador() { return entrenador; }
    public String getEspecialidad() { return especialidad; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEstado() { return estado; }
}