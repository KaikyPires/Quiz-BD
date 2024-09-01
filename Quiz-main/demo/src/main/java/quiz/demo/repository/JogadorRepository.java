package quiz.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quiz.demo.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
 
  
    @Query("SELECT AVG(j.pontuacaoTotal) FROM Jogador j")
    double findMediaPontuacaoTotal();

    @Query("SELECT j.nome, j.pontuacaoTotal " +
           "FROM Jogador j " +
           "WHERE j.pontuacaoTotal > (SELECT AVG(j2.pontuacaoTotal) FROM Jogador j2) " +
           "ORDER BY j.pontuacaoTotal DESC")
    List<Object[]> findJogadoresAcimaDaMedia();

    // Contagem de perguntas por categoria
    @Query("SELECT p.categoria, COUNT(p) FROM Pergunta p GROUP BY p.categoria ORDER BY COUNT(p) DESC")
    List<Object[]> findContagemPerguntasPorCategoria();
     // Listar jogadores e os quizzes que eles jogaram
     @Query("SELECT j.nome, q.nome, jq.data FROM Jogador j " +
     "INNER JOIN QuizJogado jq ON j.id = jq.jogador.id " +
     "INNER JOIN Quiz q ON jq.quiz.id = q.id")
List<Object[]> findJogadoresEQuizzes();

    // Listar perguntas e suas respostas associadas
    @Query("SELECT p.texto, r.texto, r.correta FROM Pergunta p " +
     "INNER JOIN Resposta r ON p.id = r.pergunta.id")
    List<Object[]> findPerguntasERespostas();

    // Listar jogadores, quizzes e as perguntas feitas em cada quiz
    @Query("SELECT j.nome, q.nome, p.texto FROM Jogador j " +
           "INNER JOIN QuizJogado jq ON j.id = jq.jogador.id " +
           "INNER JOIN Quiz q ON jq.quiz.id = q.id " +
           "INNER JOIN QuizPergunta qp ON q.id = qp.quiz.id " +
           "INNER JOIN Pergunta p ON qp.pergunta.id = p.id")
    List<Object[]> findJogadoresQuizzesEPerguntas();

    // Listar quizzes, perguntas e respostas corretas
    @Query("SELECT q.nome, p.texto, r.texto FROM Quiz q " +
           "INNER JOIN QuizPergunta qp ON q.id = qp.quiz.id " +
           "INNER JOIN Pergunta p ON qp.pergunta.id = p.id " +
           "INNER JOIN Resposta r ON p.id = r.pergunta.id " +
           "WHERE r.correta = TRUE")
    List<Object[]> findQuizzesPerguntasERespostasCorretas();
     // Número total de quizzes jogados por cada jogador
     @Query("SELECT j.nome, COUNT(jq.quiz.id) FROM Jogador j " +
     "INNER JOIN QuizJogado jq ON j.id = jq.jogador.id " +
     "GROUP BY j.nome")
List<Object[]> findTotalQuizzesJogadosPorJogador();

// Pontuação média por quiz
@Query("SELECT q.nome, AVG(jq.pontuacao) FROM Quiz q " +
     "INNER JOIN QuizJogado jq ON q.id = jq.quiz.id " +
     "GROUP BY q.nome")
List<Object[]> findPontuacaoMediaPorQuiz();


    // Datas do Primeiro e Último Quiz Jogados por Cada Jogador
    @Query("SELECT j.nome, MIN(jq.data), MAX(jq.data), DATEDIFF(MAX(jq.data), MIN(jq.data)) " +
           "FROM Jogador j " +
           "INNER JOIN QuizJogado jq ON j.id = jq.jogador.id " +
           "GROUP BY j.nome")
    List<Object[]> findDatasPrimeiroEUltimoQuizPorJogador();

    @Query("SELECT q.nome AS Quiz_Nome, j.nome AS Jogador, qj.pontuacao " +
       "FROM QuizJogado qj " +
       "INNER JOIN Quiz q ON qj.quiz.id = q.id " +
       "INNER JOIN Jogador j ON qj.jogador.id = j.id " +
       "WHERE qj.pontuacao = (" +
       "    SELECT MAX(subQj.pontuacao) " +
       "    FROM QuizJogado subQj " +
       "    WHERE subQj.quiz.id = qj.quiz.id" +
       ") " +
       "OR qj.pontuacao = (" +
       "    SELECT MIN(subQj.pontuacao) " +
       "    FROM QuizJogado subQj " +
       "    WHERE subQj.quiz.id = qj.quiz.id" +
       ") " +
       "ORDER BY qj.pontuacao DESC")
List<Object[]> findPontuacaoMaximaEMinimaPorQuiz();


}
