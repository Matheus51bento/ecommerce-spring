package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<PedidoResponseDTO> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable).map(pedidoMapper::toDTO);
    }

    public PedidoResponseDTO findById(Long id) {
        return pedidoMapper.toDTO(pedidoRepository.findById(id).orElseThrow());
    }

    @Transactional
    public PedidoResponseDTO createPedido(PedidoRequestDTO pedidoRequestDTO) {
        System.out.println("\n\n\n\nPedidoRequestDTO: " + pedidoRequestDTO.getItens());

        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);
        pedido.setCliente(cliente);
        pedido.setStatusPedido(pedidoRequestDTO.getStatusPedido());

        List<ItemPedido> itemPedidos = mapearItens(pedidoRequestDTO.getItens());
        for (ItemPedido item : itemPedidos) {
            item.setPedido(pedido);
        }

        pedido.setItens(itemPedidos);
        pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    public PedidoResponseDTO updateStatusPedido(Long id, StatusPedido statusPedido) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido não encontrado"));
        pedido.setStatusPedido(statusPedido);
        pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    public Page<PedidoResponseDTO> findByCliente(Long id, Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findByClienteId(id, pageable);
        return pedidoMapper.toDTOs(pedidos);
    }

    private List<ItemPedido> mapearItens(List<ItemPedidoRequestDTO> itemPedidoRequestDTOs) {
        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoRequestDTO itemDTO : itemPedidoRequestDTOs) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemDTO.getQuantidade());
            item.setProduto(produto);
            item.setValorUnitario(produto.getPreco()); // Define o valor unitário com base no preço do produto

            itens.add(item);
        }
        return itens;
    }
}
