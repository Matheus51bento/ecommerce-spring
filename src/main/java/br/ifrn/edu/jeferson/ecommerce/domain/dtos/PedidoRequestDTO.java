package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoRequestDTO {
    @NotBlank(message = "O status do pedido é obrigatório")
    @NotNull(message = "O status do pedido é obrigatório")
    private StatusPedido statusPedido;
    @NotBlank(message = "O cliente é obrigatório")
    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;
    @NotBlank(message = "O valor total é obrigatório")
    @NotBlank(message = "A lista de itens é obrigatória")
    private List<ItemPedidoRequestDTO> itens;
}
