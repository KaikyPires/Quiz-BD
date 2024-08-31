package quiz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import quiz.demo.model.QuizPergunta;
import quiz.demo.repository.PerguntaRepository;
import quiz.demo.service.QuizPerguntaService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/quiz-perguntas")
public class QuizPerguntaController {

    @Autowired
    private QuizPerguntaService quizPerguntaService;

    @GetMapping
    public List<QuizPergunta> getAllQuizPerguntas() {
        return quizPerguntaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizPergunta> getQuizPerguntaById(@PathVariable Long id) {
        Optional<QuizPergunta> quizPergunta = quizPerguntaService.findById(id);
        return quizPergunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuizPergunta> createQuizPergunta(@RequestBody QuizPergunta quizPergunta) {
        QuizPergunta savedQuizPergunta = quizPerguntaService.save(quizPergunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuizPergunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizPergunta> updateQuizPergunta(@PathVariable Long id, @RequestBody QuizPergunta quizPergunta) {
        if (!quizPerguntaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizPergunta.setId(id);
        QuizPergunta updatedQuizPergunta = quizPerguntaService.save(quizPergunta);
        return ResponseEntity.ok(updatedQuizPergunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizPergunta(@PathVariable Long id) {
        if (!quizPerguntaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizPerguntaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
     @Autowired
    private PerguntaRepository perguntaRepository;


}
