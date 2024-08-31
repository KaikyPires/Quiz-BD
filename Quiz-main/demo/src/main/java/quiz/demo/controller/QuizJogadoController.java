package quiz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import quiz.demo.model.QuizJogado;
import quiz.demo.service.QuizJogadoService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/quiz-jogados")
public class QuizJogadoController {

    @Autowired
    private QuizJogadoService quizJogadoService;

    @GetMapping
    public List<QuizJogado> getAllQuizJogados() {
        return quizJogadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizJogado> getQuizJogadoById(@PathVariable Long id) {
        Optional<QuizJogado> quizJogado = quizJogadoService.findById(id);
        return quizJogado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuizJogado> createQuizJogado(@RequestBody QuizJogado quizJogado) {
        QuizJogado savedQuizJogado = quizJogadoService.save(quizJogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuizJogado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizJogado> updateQuizJogado(@PathVariable Long id, @RequestBody QuizJogado quizJogado) {
        if (!quizJogadoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizJogado.setId(id);
        QuizJogado updatedQuizJogado = quizJogadoService.save(quizJogado);
        return ResponseEntity.ok(updatedQuizJogado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizJogado(@PathVariable Long id) {
        if (!quizJogadoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizJogadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
