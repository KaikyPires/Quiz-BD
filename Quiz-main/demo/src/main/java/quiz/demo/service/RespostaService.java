package quiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import quiz.demo.model.Resposta;
import quiz.demo.repository.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    public List<Resposta> findAll() {
        return respostaRepository.findAll();
    }

    public Optional<Resposta> findById(Long id) {
        return respostaRepository.findById(id);
    }

    public Resposta save(Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public void deleteById(Long id) {
        respostaRepository.deleteById(id);
    }
}
