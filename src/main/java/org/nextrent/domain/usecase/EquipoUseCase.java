package org.nextrent.domain.usecase;

import org.nextrent.domain.model.Equipo;
import org.nextrent.infrastructure.drivenadapters.jpa.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoUseCase {

    private final EquipoRepository equipoRepository;

    public EquipoUseCase(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> obtenerPorId(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo crearEquipo(Equipo equipo) {
        // ✅ Validar si ya existe un equipo con el mismo nombre
        Optional<Equipo> existente = equipoRepository.findByNombre(equipo.getNombre());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un equipo con el nombre: " + equipo.getNombre());
        }

        equipo.setArrendado(false);
        return equipoRepository.save(equipo);
    }
}
