package org.nextrent.infrastructure.drivenadapters.jpa;

import org.nextrent.domain.model.Equipo;
import org.nextrent.domain.usecase.puertos.EquipoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EquipoRepositoryAdapter implements EquipoRepositoryPort {

    private final EquipoRepository equipoRepository;

    public EquipoRepositoryAdapter(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public List<Equipo> listar() {
        return equipoRepository.findAll();
    }

    @Override
    public Optional<Equipo> buscarPorId(Long id) {
        return equipoRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }
}
