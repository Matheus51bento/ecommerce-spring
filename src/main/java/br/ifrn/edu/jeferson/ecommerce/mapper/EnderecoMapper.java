package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente.id", source = "clienteId")
    Endereco toEntity(EnderecoRequestDTO dto);

    List<EnderecoResponseDTO> toDTOList(List<Endereco> enderecos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    void updateEntityFromDTO(EnderecoRequestDTO dto, @MappingTarget Endereco endereco);

    default Cliente map(Long clienteId) {
        if (clienteId == null) {
            return null;
        }
        var cliente = new Cliente();
        cliente.setId(clienteId);
        return cliente;
    }

}
