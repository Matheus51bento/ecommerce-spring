package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public EnderecoResponseDTO salvar(EnderecoRequestDTO enderecoDto) {
        Cliente cliente = clienteRepository.findById(enderecoDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente not found"));

        if (enderecoRepository.existsByCliente(cliente)) {
            throw new BusinessException("Já existe um endereço para esse cliente");
        }

        var endereco = enderecoMapper.toEntity(enderecoDto);
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(endereco);
    }

    public List<EnderecoResponseDTO> lista(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecoMapper.toDTOList(enderecos);
    }

    public void deletar(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }
        enderecoRepository.deleteById(id);
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO enderecoDto) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Endereço não encontrado"));
        enderecoMapper.updateEntityFromDTO(enderecoDto, endereco);
        var enderecoAlterado = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(enderecoAlterado);
    }

    public EnderecoResponseDTO buscarPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Endereço não encontrado"));
        return enderecoMapper.toResponseDTO(endereco);
    }


}
