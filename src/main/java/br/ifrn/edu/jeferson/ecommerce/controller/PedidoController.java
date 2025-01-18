package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;


    @GetMapping
    public Page<PedidoResponseDTO> getAllPedidos(Pageable pageable) {
        return pedidoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO getPedidoById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    public PedidoResponseDTO createPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        return pedidoService.createPedido(pedidoRequestDTO);
    }

    @PatchMapping("update-status/{id}")
    public PedidoResponseDTO updateStatusPedido(@PathVariable Long id, @RequestParam StatusPedido statusPedido) {
        return pedidoService.updateStatusPedido(id, statusPedido);
    }

    @GetMapping("/cliente/{id}")
    public Page<PedidoResponseDTO> getPedidosByCliente(@PathVariable Long id, Pageable pageable) {
        return pedidoService.findByCliente(id, pageable);
    }

}
