package org.nextrent.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private boolean arrendado;

    public Equipo() {}

    public Equipo(Long id, String nombre, String tipo, boolean arrendado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.arrendado = arrendado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isArrendado() { return arrendado; }
    public void setArrendado(boolean arrendado) { this.arrendado = arrendado; }
}
