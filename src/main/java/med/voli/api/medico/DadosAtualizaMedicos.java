package med.voli.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voli.api.endereco.DadosEndereco;

public record DadosAtualizaMedicos(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco) {

}
