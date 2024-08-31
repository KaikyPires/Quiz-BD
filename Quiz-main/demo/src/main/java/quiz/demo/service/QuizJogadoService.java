package quiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import quiz.demo.model.QuizJogado;
import quiz.demo.repository.QuizJogadoRepository;

@Service
public class QuizJogadoService {

    @Autowired
    private QuizJogadoRepository quizJogadoRepository;

    public List<QuizJogado> findAll() {
        return quizJogadoRepository.findAll();
    }

    public Optional<QuizJogado> findById(Long id) {
        return quizJogadoRepository.findById(id);
    }

    public QuizJogado save(QuizJogado quizJogado) {
        return quizJogadoRepository.save(quizJogado);
    }

    public void deleteById(Long id) {
        quizJogadoRepository.deleteById(id);
    }
}
