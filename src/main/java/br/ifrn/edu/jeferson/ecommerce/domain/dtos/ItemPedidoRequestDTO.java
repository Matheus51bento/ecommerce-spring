package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoRequestDTO {
    private Long produtoId;
    private Integer quantidade;
}
