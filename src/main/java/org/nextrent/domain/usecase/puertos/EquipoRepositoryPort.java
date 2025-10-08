package org.nextrent.domain.usecase.puertos;

import org.nextrent.domain.model.Equipo;
import java.util.List;
import java.util.Optional;

public interface EquipoRepositoryPort {
    Equipo guardar(Equipo equipo);
    List<Equipo> listar();
    Optional<Equipo> buscarPorId(Long id);
    void eliminar(Long id);
}