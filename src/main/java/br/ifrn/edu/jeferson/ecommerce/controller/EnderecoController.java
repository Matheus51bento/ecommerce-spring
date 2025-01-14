package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/enderecos")
@Tag(name = "Enderecos", description = "API de gerenciamento de endereços dos clientes")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> salvar(@RequestBody EnderecoRequestDTO enderecoDto) {

        return ResponseEntity.ok(enderecoService.salvar(enderecoDto));
    }

    @Operation
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar() {
        return ResponseEntity.ok(enderecoService.lista());
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoDto) {
        return ResponseEntity.ok(enderecoService.atualizar(id, enderecoDto));
    }

}
