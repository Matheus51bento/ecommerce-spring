package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API de gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Lista todos os produtos com paginação",
            description = "Permite buscar produtos por nome de forma opcional e mantém a paginação.")
    @GetMapping
    public Page<ProdutoResponseDTO> getAllProdutos(
            @Parameter(description = "Nome do produto para filtrar os resultados", example = "Celular")
            @RequestParam(required = false) String nome,
            @Parameter(description = "Paginação dos resultados")
            Pageable pageable) {
        return produtoService.getAllProdutos(nome, pageable);
    }


    @GetMapping("/{id}")
    public ProdutoResponseDTO getProdutoById(@PathVariable Long id) {
        return produtoService.getProdutoById(id);
    }

    @PostMapping
    public ProdutoResponseDTO createProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.createProduto(produtoRequestDTO);
    }

    @PatchMapping("/{id}")
    public ProdutoResponseDTO updateProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.updateProduto(id, produtoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
    }

    @PostMapping("/{id}/estoque")
    @Tag(name = "Estoque", description = "API de gerenciamento de estoque, você pode adicionar ou remover produtos do estoque")
    public ProdutoResponseDTO adicionarEstoque(@PathVariable Long id, @RequestParam Integer quantidade) {
        return produtoService.atualizarEstoque(id, quantidade);
    }

    @GetMapping("/{id}/categorias")
    public Page<ProdutoResponseDTO> getProdutosByCategoria(@PathVariable Long id, Pageable pageable) {
        return produtoService.listarPorCartegoria(id, pageable);
    }

}
