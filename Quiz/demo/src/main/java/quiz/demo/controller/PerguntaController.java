package quiz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import quiz.demo.model.Pergunta;
import quiz.demo.service.PerguntaService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping
    public List<Pergunta> getAllPerguntas() {
        return perguntaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> getPerguntaById(@PathVariable Long id) {
        Optional<Pergunta> pergunta = perguntaService.findById(id);
        return pergunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pergunta> createPergunta(@RequestBody Pergunta pergunta) {
        Pergunta savedPergunta = perguntaService.save(pergunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPergunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pergunta> updatePergunta(@PathVariable Long id, @RequestBody Pergunta pergunta) {
        if (!perguntaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pergunta.setId(id);
        Pergunta updatedPergunta = perguntaService.save(pergunta);
        return ResponseEntity.ok(updatedPergunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePergunta(@PathVariable Long id) {
        if (!perguntaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        perguntaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
