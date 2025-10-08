package org.nextrent.infrastructure.entrypoints;

import org.nextrent.domain.model.Cliente;
import org.nextrent.infrastructure.drivenadapters.jpa.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class NextRentController {

    private final ClienteRepository clienteRepository;

    public NextRentController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

}
