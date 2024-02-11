package med.voli.api.controloller;

import jakarta.validation.Valid;

import med.voli.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos") //http://msdae:8080/contribuinte
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarmedico(@RequestBody @Valid DadosCadastroMedicos dados){
        System.out.println(dados);
        repository.save(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(sort={"nome"}) Pageable paginacao){
         System.out.println(paginacao);
         //return repository.findAll(paginacao).map(DadosListagemMedico::new);
        //return repository.findAllByUserativoTrue(paginacao).map(DadosListagemMedico::new);
        return repository.findAllByUserativoTrue(paginacao).map(DadosListagemMedico::new);
    }
    @PutMapping
    @Transactional
    public void atualizarmedico(@RequestBody @Valid DadosAtualizaMedicos dados){
        var lermedico = repository.getReferenceById(dados.id());
        lermedico.atualizarinformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        //repository.deleteById(id); --> Apaga o registro no banco de dados fisicamente
        var lermedico = repository.getReferenceById(id);
        lermedico.excluir();

    }
}
