package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoMapper produtoMapper;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .map(produtoMapper::toDTO)
                .orElseThrow();
    }

    public ProdutoResponseDTO createProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        List<Categoria> categorias = categoriaRepository.findAllById(produtoRequestDTO.getCategoriaIds());
        produto.setCategorias(categorias);
        Produto savedProduto = produtoRepository.save(produto);
        return produtoMapper.toDTO(savedProduto);
    }

    public ProdutoResponseDTO updateProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        System.out.println("\n\n\nIds: " + produtoRequestDTO.getCategoriaIds());
        List<Categoria> categorias = categoriaRepository.findAllById(produtoRequestDTO.getCategoriaIds());
        Produto updated = produtoMapper.toEntity(produtoRequestDTO);
        updated.setId(produto.getId());
        updated.setCategorias(categorias);
        Produto savedProduto = produtoRepository.save(updated);
        return produtoMapper.toDTO(savedProduto);
    }

    public Page<ProdutoResponseDTO> getAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(produtoMapper::toDTO);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, Integer quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setEstoque(produto.getEstoque() + quantidade);
        Produto updated = produtoRepository.save(produto);
        return produtoMapper.toDTO(updated);
    }

    public Page<ProdutoResponseDTO> listarPorCartegoria(Long id, Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findByCategoriasId(id,pageable);
        return produtos.map(produtoMapper::toDTO);
    }

}
