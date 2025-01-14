package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientResponseDTO {

    @NotNull(message = "Nome é obrigatório")
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Endereco endereco;
//    private Set<Pedido> pedidos;
}
