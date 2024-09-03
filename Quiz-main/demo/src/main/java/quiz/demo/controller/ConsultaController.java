package quiz.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quiz.demo.model.Jogador;
import quiz.demo.model.Quiz;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{consulta}")
    public ResponseEntity<List<Map<String, Object>>> getConsulta(@PathVariable String consulta) {
        String sql = "";
        switch (consulta) {
            case "pontuacao-acima":
                sql = "SELECT j.nome, j.Pontuacao_Total " +
                        "FROM jogador j " +
                        "WHERE j.Pontuacao_Total > ( " +
                        "    SELECT AVG(j1.Pontuacao_Total) " +
                        "    FROM jogador j1 " +
                        ")";

                break;
            case "contagem-perguntas":
                sql = "SELECT Categoria, COUNT(*) AS Total_Perguntas FROM Pergunta GROUP BY Categoria ORDER BY Total_Perguntas DESC";
                break;
            case "jogadores-quizzes":
                sql = "SELECT Jogador.Nome, Quiz.Nome AS Quiz_Nome, Quiz_Jogado.Data " +
                        "FROM Jogador " +
                        "INNER JOIN Quiz_Jogado ON Jogador.ID = Quiz_Jogado.Jogador_ID " +
                        "INNER JOIN Quiz ON Quiz_Jogado.Quiz_ID = Quiz.ID";
                break;
            case "perguntas-respostas":
                sql = "SELECT Pergunta.Texto AS Pergunta, Resposta.Texto AS Resposta, Resposta.Correta " +
                        "FROM Pergunta " +
                        "INNER JOIN Resposta ON Pergunta.ID = Resposta.Pergunta_ID";
                break;
            case "jogadores-quizzes-perguntas":
                sql = "SELECT Jogador.Nome AS Jogador, Quiz.Nome AS Quiz, Pergunta.Texto AS Pergunta " +
                        "FROM Jogador " +
                        "INNER JOIN Quiz_Jogado ON Jogador.ID = Quiz_Jogado.Jogador_ID " +
                        "INNER JOIN Quiz ON Quiz_Jogado.Quiz_ID = Quiz.ID " +
                        "INNER JOIN Quiz_Pergunta ON Quiz.ID = Quiz_Pergunta.Quiz_ID " +
                        "INNER JOIN Pergunta ON Quiz_Pergunta.Pergunta_ID = Pergunta.ID";
                break;
            case "quizzes-perguntas-respostas-corr":
                sql = "SELECT Quiz.Nome AS Quiz, Pergunta.Texto AS Pergunta, Resposta.Texto AS Resposta " +
                        "FROM Quiz " +
                        "INNER JOIN Quiz_Pergunta ON Quiz.ID = Quiz_Pergunta.Quiz_ID " +
                        "INNER JOIN Pergunta ON Quiz_Pergunta.Pergunta_ID = Pergunta.ID " +
                        "INNER JOIN Resposta ON Pergunta.ID = Resposta.Pergunta_ID " +
                        "WHERE Resposta.Correta = TRUE";
                break;
            case "total-quizzes-jogados":
                sql = "SELECT Jogador.Nome, COUNT(Quiz_Jogado.Quiz_ID) AS Total_Quizzes_Jogados " +
                        "FROM Jogador " +
                        "INNER JOIN Quiz_Jogado ON Jogador.ID = Quiz_Jogado.Jogador_ID " +
                        "GROUP BY Jogador.Nome";
                break;
            case "pontuacao-media":
                sql = "SELECT Quiz.Nome, AVG(Quiz_Jogado.Pontuacao) AS Pontuacao_Media " +
                        "FROM Quiz " +
                        "INNER JOIN Quiz_Jogado ON Quiz.ID = Quiz_Jogado.Quiz_ID " +
                        "GROUP BY Quiz.Nome";
                break;
            case "datas-quiz":
                sql = "SELECT Jogador.Nome AS Nome_Jogador, MIN(Quiz_Jogado.Data) AS Data_Primeiro_Quiz, " +
                        "MAX(Quiz_Jogado.Data) AS Data_Ultimo_Quiz, " +
                        "DATEDIFF(MAX(Quiz_Jogado.Data), MIN(Quiz_Jogado.Data)) AS Dias_Entre_Quizzes " +
                        "FROM Jogador " +
                        "INNER JOIN Quiz_Jogado ON Jogador.ID = Quiz_Jogado.Jogador_ID " +
                        "GROUP BY Jogador.Nome";
                break;
            case "total-pontuacao-quiz":
                sql = "SELECT jogador.id, " +
                        "jogador.nome, " +
                        "jogador.Pontuacao_Total AS Total_Pontuacao, " +
                        "COUNT(quiz_jogado.Jogador_ID) AS Quiz_Count " +
                        "FROM jogador " +
                        "INNER JOIN quiz_jogado ON jogador.ID = quiz_jogado.Jogador_ID " +
                        "GROUP BY jogador.id " +
                        "ORDER BY Total_Pontuacao DESC, Quiz_Count DESC;";

                break;
            case "Top-10 - SQL":
                sql = "SELECT jogador.id, jogador.nome, SUM(quiz_jogado.Pontuacao) AS Total_Pontuacao, " +
                        "COUNT(quiz_jogado.Jogador_ID) AS Quiz_Count " +
                        "FROM jogador " +
                        "INNER JOIN quiz_jogado ON jogador.ID = quiz_jogado.Jogador_ID " +
                        "WHERE quiz_jogado.Quiz_ID = 1 " +
                        "GROUP BY jogador.id, jogador.nome " +
                        "ORDER BY Total_Pontuacao DESC, Quiz_Count DESC;";
                break;
            case "Top-10 - Programação":
                sql = "SELECT jogador.id, jogador.nome, SUM(quiz_jogado.Pontuacao) AS Total_Pontuacao, " +
                        "COUNT(quiz_jogado.Jogador_ID) AS Quiz_Count " +
                        "FROM jogador " +
                        "INNER JOIN quiz_jogado ON jogador.ID = quiz_jogado.Jogador_ID " +
                        "WHERE quiz_jogado.Quiz_ID = 2 " +
                        "GROUP BY jogador.id, jogador.nome " +
                        "ORDER BY Total_Pontuacao DESC, Quiz_Count DESC;";
                break;
            case "Top-10 - Games":
                sql = "SELECT jogador.id, jogador.nome, SUM(quiz_jogado.Pontuacao) AS Total_Pontuacao, " +
                        "COUNT(quiz_jogado.Jogador_ID) AS Quiz_Count " +
                        "FROM jogador " +
                        "INNER JOIN quiz_jogado ON jogador.ID = quiz_jogado.Jogador_ID " +
                        "WHERE quiz_jogado.Quiz_ID = 3 " +
                        "GROUP BY jogador.id, jogador.nome " +
                        "ORDER BY Total_Pontuacao DESC, Quiz_Count DESC;";
                break;
            case "Listar jogador, quiz, data, pontuacao":
                sql = "SELECT Jogador.Nome AS Nome_Jogador, " +
                        "Quiz.Nome AS Nome_Quiz, " +
                        "Quiz_Jogado.Data AS Data_Jogo, " +
                        "Quiz_Jogado.Pontuacao AS Pontuacao_Obtida " +
                        "FROM Jogador " +
                        "INNER JOIN Quiz_Jogado ON Jogador.ID = Quiz_Jogado.Jogador_ID " +
                        "INNER JOIN Quiz ON Quiz_Jogado.Quiz_ID = Quiz.ID " +
                        "ORDER BY Nome_Jogador, Data_Jogo;";
                break;

            default:
                return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(resultados);
    }

}
