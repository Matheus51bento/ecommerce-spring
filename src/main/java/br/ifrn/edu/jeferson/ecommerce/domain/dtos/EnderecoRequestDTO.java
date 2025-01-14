package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de endereço")
public class EnderecoRequestDTO {

    @Schema(description = "Rua do endereço", example = "Rua dos Bobos")
    @NotBlank(message = "Rua é obrigatória")
    private String rua;

    @Schema(description = "Número do endereço", example = "0")
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Schema(description = "Bairro do endereço", example = "Bairro Feliz")
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "Natal")
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @Schema(description = "Estado do endereço", example = "RN")
    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @Schema(description = "CEP do endereço", example = "59000-000")
    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @Getter
    @Schema(description = "Cliente do endereço")
    private Long clienteId;

}
