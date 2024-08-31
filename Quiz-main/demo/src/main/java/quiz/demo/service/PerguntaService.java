package quiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import quiz.demo.model.Pergunta;
import quiz.demo.repository.PerguntaRepository;
@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    public List<Pergunta> findAll() {
        return perguntaRepository.findAll();
    }

    public Optional<Pergunta> findById(Long id) {
        return perguntaRepository.findById(id);
    }

    public Pergunta save(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public void deleteById(Long id) {
        perguntaRepository.deleteById(id);
    }
}
