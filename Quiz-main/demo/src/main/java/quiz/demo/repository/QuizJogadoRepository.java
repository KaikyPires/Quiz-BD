package quiz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.demo.model.QuizJogado;

public interface QuizJogadoRepository extends JpaRepository<QuizJogado, Long> {
    
}
