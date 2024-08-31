package quiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import quiz.demo.model.Jogador;
import quiz.demo.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    public Optional<Jogador> findById(Long id) {
        return jogadorRepository.findById(id);
    }

    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public void deleteById(Long id) {
        jogadorRepository.deleteById(id);
    }

    public List<Object[]> findJogadoresComPontuacaoAcima(double pontuacao) {
        return jogadorRepository.findJogadoresComPontuacaoAcima(pontuacao);
    }

    public List<Object[]> findContagemPerguntasPorCategoria() {
        return jogadorRepository.findContagemPerguntasPorCategoria();
    }

    public List<Object[]> findJogadoresEQuizzes() {
        return jogadorRepository.findJogadoresEQuizzes();
    }

    public List<Object[]> findPerguntasERespostas() {
        return jogadorRepository.findPerguntasERespostas();
    }

    public List<Object[]> findJogadoresQuizzesEPerguntas() {
        return jogadorRepository.findJogadoresQuizzesEPerguntas();
    }

    public List<Object[]> findQuizzesPerguntasERespostasCorretas() {
        return jogadorRepository.findQuizzesPerguntasERespostasCorretas();
    }

    public List<Object[]> findTotalQuizzesJogadosPorJogador() {
        return jogadorRepository.findTotalQuizzesJogadosPorJogador();
    }

    public List<Object[]> findPontuacaoMediaPorQuiz() {
        return jogadorRepository.findPontuacaoMediaPorQuiz();
    }

    public List<Object[]> findDatasPrimeiroEUltimoQuizPorJogador() {
        return jogadorRepository.findDatasPrimeiroEUltimoQuizPorJogador();
    }
    public List<Object[]> findPontuacaoMaximaEMinimaPorQuiz() {
        return jogadorRepository.findPontuacaoMaximaEMinimaPorQuiz();
    }
    
}
