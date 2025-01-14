package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClientResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "endereco", target = "endereco")
    Cliente toEntity(ClientRequestDTO clientRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "endereco", target = "endereco")
    ClientResponseDTO toDTO(Cliente cliente);

    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);
    EnderecoRequestDTO toDTO(Endereco endereco);
}
