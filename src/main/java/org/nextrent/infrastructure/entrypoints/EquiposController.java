package org.nextrent.infrastructure.entrypoints;

import org.nextrent.domain.model.Cliente;
import org.nextrent.domain.model.Equipo;
import org.nextrent.domain.usecase.ClienteUseCase;
import org.nextrent.domain.usecase.EquipoUseCase;
import org.nextrent.infrastructure.drivenadapters.jpa.ClienteRepository;
import org.nextrent.infrastructure.drivenadapters.jpa.EquipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
@CrossOrigin(origins = "*")
public class EquiposController {

    private final EquipoUseCase equipoUseCase;
    private final EquipoRepository equipoRepository;
    private final ClienteRepository clienteRepository;

    public EquiposController(EquipoUseCase equipoUseCase,
                             EquipoRepository equipoRepository,
                             ClienteRepository clienteRepository) {
        this.equipoUseCase = equipoUseCase;
        this.equipoRepository = equipoRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Equipo>> listarEquipos() {
        return ResponseEntity.ok(equipoUseCase.listarEquipos());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEquipo(@RequestBody Equipo equipo) {
        try {
            Equipo nuevo = equipoUseCase.crearEquipo(equipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/asignar")
    public ResponseEntity<String> asignar(
            @RequestParam Long clienteId,
            @RequestParam Long equipoId) {

        Optional<Equipo> equipoOpt = equipoUseCase.obtenerPorId(equipoId);
        if (equipoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado");
        }

        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        Equipo equipo = equipoOpt.get();
        Cliente cliente = clienteOpt.get();

        if (equipo.isArrendado()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El equipo ya está arrendado");
        }

        // Asignar equipo al cliente
        equipo.setCliente(cliente);
        equipo.setArrendado(true);
        equipoRepository.save(equipo);

        // Actualizar estado del cliente
        cliente.setArrendado(true);
        clienteRepository.save(cliente);

        return ResponseEntity.ok("Equipo asignado correctamente");
    }

    @PutMapping("/liberar")
    public ResponseEntity<String> liberar(@RequestParam Long equipoId) {
        Optional<Equipo> equipoOpt = equipoUseCase.obtenerPorId(equipoId);
        if (equipoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado");
        }

        Equipo equipo = equipoOpt.get();
        if (!equipo.isArrendado()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El equipo ya está libre");
        }

        // Verificar si el cliente tiene más equipos asignados
        Cliente cliente = equipo.getCliente();
        equipo.setArrendado(false);
        equipo.setCliente(null);
        equipoRepository.save(equipo);

        // Solo marcar cliente como no arrendado si no tiene más equipos
        if (cliente != null) {
            long equiposDelCliente = equipoRepository.findAll().stream()
                    .filter(e -> e.getCliente() != null && e.getCliente().getId().equals(cliente.getId()))
                    .count();

            if (equiposDelCliente == 0) {
                cliente.setArrendado(false);
                clienteRepository.save(cliente);
            }
        }

        return ResponseEntity.ok("Equipo liberado correctamente");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam Long id) {
        Optional<Equipo> equipoOpt = equipoUseCase.obtenerPorId(id);
        if (equipoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado");
        }

        equipoRepository.deleteById(id);
        return ResponseEntity.ok("Equipo eliminado correctamente");
    }
}