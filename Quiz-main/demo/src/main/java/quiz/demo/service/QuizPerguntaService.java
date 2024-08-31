package quiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import quiz.demo.model.QuizPergunta;
import quiz.demo.repository.QuizPerguntaRepository;

@Service
public class QuizPerguntaService {

    @Autowired
    private QuizPerguntaRepository quizPerguntaRepository;

    public List<QuizPergunta> findAll() {
        return quizPerguntaRepository.findAll();
    }

    public Optional<QuizPergunta> findById(Long id) {
        return quizPerguntaRepository.findById(id);
    }

    public QuizPergunta save(QuizPergunta quizPergunta) {
        return quizPerguntaRepository.save(quizPergunta);
    }

    public void deleteById(Long id) {
        quizPerguntaRepository.deleteById(id);
    }
}
