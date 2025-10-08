package org.nextrent.infrastructure.drivenadapters.jpa;

import org.nextrent.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
