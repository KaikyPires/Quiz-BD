package quiz.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quiz.demo.model.QuizPergunta;

public interface QuizPerguntaRepository extends JpaRepository<QuizPergunta, Long> {
   
}
