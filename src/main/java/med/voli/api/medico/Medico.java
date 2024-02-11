package med.voli.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.endereco.Endereco;

@Table(name="medicos")
@Entity(name="Medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean userativo;
    public Medico(DadosCadastroMedicos dados) {
        this.nome = dados.nome().toUpperCase();
        this.email = dados.email().toUpperCase();
        this.telefone = dados.telefone().toUpperCase();
        this.crm = dados.crm().toUpperCase();
        this.userativo = true;
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
    public void atualizarinformacoes(DadosAtualizaMedicos dados) {
        if (dados.nome() != null){
            this.nome = dados.nome().toUpperCase();
        }
        if (dados.email() != null){
            this.email = dados.email().toUpperCase();
        }
        if (dados.telefone() != null){
            this.nome = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }


    }

    public void excluir() {
        this.userativo = false;
    }
}
