package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO para requisição de cliente")
public class ClientRequestDTO {

    @Schema(description = "Nome do cliente", example = "João Silva")
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Schema(description = "Email do cliente", example = "joao.silva@example.com")
    @Email(message = "O email deve ser válido.")
    private String email;

    @Schema(description = "CPF do cliente", example = "123.456.789-00")
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @Schema(description = "Telefone do cliente", example = "(84) 98765-4321")
    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @Schema(description = "Endereço do cliente")
    @NotNull(message = "Endereço é obrigatório")
    private EnderecoRequestDTO endereco;
}
