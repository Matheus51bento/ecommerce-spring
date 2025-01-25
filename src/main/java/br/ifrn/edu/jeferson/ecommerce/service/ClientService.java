package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClientMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public ClientResponseDTO createCliente(ClientRequestDTO clientRequestDTO) {

        Cliente cliente = clientMapper.toEntity(clientRequestDTO);

        Endereco endereco = clientMapper.toEntity(clientRequestDTO.getEndereco());
        endereco = enderecoRepository.save(endereco);

        cliente.setEndereco(endereco);
        Cliente savedCliente = clienteRepository.save(cliente);

        endereco.setCliente(savedCliente);
        enderecoRepository.save(endereco);

        return clientMapper.toDTO(savedCliente);
    }

    public Page<ClientResponseDTO> getAllClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(clientMapper::toDTO);
    }

    public Optional<ClientResponseDTO> getClienteById(Long id) {
        return clienteRepository.findById(id)
                .map(clientMapper::toDTO);
    }

    public ClientResponseDTO updateCliente(Long id, ClientRequestDTO clientRequestDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        Cliente updated = clientMapper.toEntity(clientRequestDTO);
        updated.setId(cliente.getId());
        Cliente savedCliente = clienteRepository.save(updated);
        return clientMapper.toDTO(savedCliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
