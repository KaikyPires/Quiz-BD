package quiz.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import quiz.demo.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
