package quiz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.demo.model.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    
}
