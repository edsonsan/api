package med.voli.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voli.api.endereco.DadosEndereco;

public record DadosCadastroMedicos(
                                   @NotBlank
                                   String nome,
                                   @NotBlank
                                   @Email
                                   String email,
                                   @NotBlank
                                   String telefone,
                                   @NotBlank
                                   @Pattern(regexp = "\\d{4,6}")
                                   String crm,
                                   @NotNull
                                   Especialidade especialidade,
                                   @NotNull
                                   @Valid
                                   DadosEndereco endereco) {
}
