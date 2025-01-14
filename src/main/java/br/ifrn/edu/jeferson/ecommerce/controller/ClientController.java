package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClientMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API de gerenciamento de clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ClientResponseDTO createCliente(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        System.out.printf("ClientRequestDTO: %s\n", clientRequestDTO);
        return clientService.createCliente(clientRequestDTO);
    }

    @GetMapping
    public Page<ClientResponseDTO> getAllClientes(Pageable pageable) {
        return clientService.getAllClientes(pageable);
    }

    @GetMapping("/{id}")
    public Optional<ClientResponseDTO> getClienteById(@PathVariable Long id) {
        return clientService.getClienteById(id);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ClientResponseDTO updateCliente(@PathVariable Long id,@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.updateCliente(id, clientRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCliente(@PathVariable Long id) {
        clientService.deleteCliente(id);
    }

}
