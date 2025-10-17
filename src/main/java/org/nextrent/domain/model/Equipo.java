package org.nextrent.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private boolean arrendado;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    public Equipo() {}

    public Equipo(Long id, String nombre, String tipo, boolean arrendado, Cliente cliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.arrendado = arrendado;
        this.cliente = cliente;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isArrendado() { return arrendado; }
    public void setArrendado(boolean arrendado) { this.arrendado = arrendado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
