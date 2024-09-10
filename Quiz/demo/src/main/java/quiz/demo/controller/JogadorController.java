package quiz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import quiz.demo.model.Jogador;
import quiz.demo.repository.JogadorRepository;
import quiz.demo.service.JogadorService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public List<Jogador> getAllJogadores() {
        return jogadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorService.findById(id);
        return jogador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {
        Jogador savedJogador = jogadorService.save(jogador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable Long id, @RequestBody Jogador jogador) {
        if (!jogadorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jogador.setId(id);
        Jogador updatedJogador = jogadorService.save(jogador);
        return ResponseEntity.ok(updatedJogador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJogador(@PathVariable Long id) {
        if (!jogadorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jogadorService.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }

    @GetMapping("/pontuacao-acima")
    public ResponseEntity<List<Object[]>> getJogadoresAcimaDaMedia() {
        List<Object[]> jogadores = jogadorService.getJogadoresAcimaDaMedia();
        return ResponseEntity.ok(jogadores);
    }
    @GetMapping("/contagem-perguntas")
    public List<Object[]> getContagemPerguntasPorCategoria() {
        return jogadorService.findContagemPerguntasPorCategoria();
    }

    @GetMapping("/jogadores-quizzes")
    public List<Object[]> getJogadoresEQuizzes() {
        return jogadorService.findJogadoresEQuizzes();
    }

    @GetMapping("/perguntas-respostas")
    public List<Object[]> getPerguntasERespostas() {
        return jogadorService.findPerguntasERespostas();
    }

    @GetMapping("/jogadores-quizzes-perguntas")
    public List<Object[]> getJogadoresQuizzesEPerguntas() {
        return jogadorService.findJogadoresQuizzesEPerguntas();
    }

    @GetMapping("/quizzes-perguntas-respostas-corr")
    public List<Object[]> getQuizzesPerguntasERespostasCorretas() {
        return jogadorService.findQuizzesPerguntasERespostasCorretas();
    }

    @GetMapping("/total-quizzes-jogados")
    public List<Object[]> getTotalQuizzesJogadosPorJogador() {
        return jogadorService.findTotalQuizzesJogadosPorJogador();
    }

    @GetMapping("/pontuacao-media")
    public List<Object[]> getPontuacaoMediaPorQuiz() {
        return jogadorService.findPontuacaoMediaPorQuiz();
    }

    @GetMapping("/datas-quiz")
    public List<Object[]> getDatasPrimeiroEUltimoQuizPorJogador() {
        return jogadorService.findDatasPrimeiroEUltimoQuizPorJogador();
    }

    @GetMapping("/pontuacao-maxima-minima")
public List<Object[]> getPontuacaoMaximaEMinimaPorQuiz() {
    return jogadorService.findPontuacaoMaximaEMinimaPorQuiz();
}





}