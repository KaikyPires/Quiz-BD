package quiz.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quiz.demo.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
     
}
