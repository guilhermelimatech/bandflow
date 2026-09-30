package BandFlow;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicos")
public class MusicoController {

    private final MusicoRepository repository;

    public MusicoController(MusicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Musico> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Musico cadastrar(@RequestBody Musico musico) {
        return repository.save(musico);
    }

    @PutMapping("/{id}")
    public Musico editar(@PathVariable Long id, @RequestBody Musico musico) {

        Musico existente = repository.findById(id).orElseThrow();

        existente.setNome(musico.getNome());
        existente.setInstrumento(musico.getInstrumento());
        existente.setTelefone(musico.getTelefone());

        return repository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}