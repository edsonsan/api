package med.voli.api.controloller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.medico.DadosListagemMedico;
import med.voli.api.paciente.DadosCadastroPaciente;
import med.voli.api.paciente.DadosListagemPaciente;
import med.voli.api.paciente.Paciente;
import med.voli.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrarpaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(sort={"nome"}) Pageable paginacao) {
        System.out.println(paginacao);
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

}
