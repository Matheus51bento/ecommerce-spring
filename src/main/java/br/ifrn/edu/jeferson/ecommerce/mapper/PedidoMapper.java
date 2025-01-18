package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido toEntity(PedidoRequestDTO pedidoRequestDTO);

    PedidoResponseDTO toDTO(Pedido pedido);

    default Page<PedidoResponseDTO> toDTOs(Page<Pedido> pedidos) {
        return pedidos.map(this::toDTO);
    }

}
