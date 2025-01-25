package br.ifrn.edu.jeferson.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @OneToOne(mappedBy = "cliente")
    @JsonBackReference
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @PreRemove
    private void validarRemocao() {
        if (pedidos != null && !pedidos.isEmpty()) {
            throw new IllegalStateException("Não é possível deletar um cliente que possui pedidos associados.");
        }
    }

}
