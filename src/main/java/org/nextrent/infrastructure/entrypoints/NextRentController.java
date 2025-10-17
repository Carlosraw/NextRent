package org.nextrent.infrastructure.entrypoints;

import org.nextrent.domain.model.Cliente;
import org.nextrent.domain.usecase.ClienteUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class NextRentController {

    private final ClienteUseCase clienteUseCase;

    public NextRentController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteUseCase.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteUseCase.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteUseCase.crearCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteUseCase.eliminarCliente(id);
    }
}
