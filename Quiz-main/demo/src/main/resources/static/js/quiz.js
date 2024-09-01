const question = document.querySelector(".question");
const answers = document.querySelector(".answers");
const spnQtd = document.querySelector(".spnQtd");
const textFinish = document.querySelector(".finish span");
const content = document.querySelector(".content");
const contentFinish = document.querySelector(".finish");
const btnRestart = document.querySelector(".btn-restart");
const btnSave = document.querySelector(".btn-save"); // Novo botão para salvar pontuação

let currentIndex = 0;
let questionsCorrect = 0;
let questions = []; // Inicialmente vazio, será preenchido com dados do banco

btnRestart.onclick = () => {
  content.style.display = "flex";
  contentFinish.style.display = "none";

  currentIndex = 0;
  questionsCorrect = 0;
  loadQuestion();
};

btnSave.onclick = () => {
  const nome = prompt("Digite seu nome:");
  if (nome) {
    fetch('http://localhost:8080/api/jogadores')
      .then(response => response.json())
      .then(jogadores => {
        const jogadorExistente = jogadores.find(j => j.nome === nome);

        if (jogadorExistente) {
          // Atualizar a pontuação do jogador existente
          const updatedScore = jogadorExistente.pontuacaoTotal + questionsCorrect;

          fetch(`http://localhost:8080/api/jogadores/${jogadorExistente.id}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              ...jogadorExistente,
              pontuacaoTotal: updatedScore
            })
          })
          .then(response => response.json())
          .then(updatedJogador => {
            console.log('Pontuação atualizada com sucesso:', updatedJogador);
            window.location.href = 'index.html'; // Redirecionar para a página inicial
          })
          .catch(error => console.error('Erro ao atualizar a pontuação:', error));
        } else {
          // Criar um novo jogador com a pontuação
          fetch('http://localhost:8080/api/jogadores', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              nome: nome,
              pontuacaoTotal: questionsCorrect
            })
          })
          .then(response => response.json())
          .then(newJogador => {
            console.log('Novo jogador criado com sucesso:', newJogador);
            window.location.href = 'index.html'; // Redirecionar para a página inicial
          })
          .catch(error => console.error('Erro ao criar o novo jogador:', error));
        }
      })
      .catch(error => console.error('Erro ao buscar jogadores:', error));
  }
};

function nextQuestion(e) {
  if (e.target.getAttribute("data-correct") === "true") {
    questionsCorrect++;
  }

  if (currentIndex < questions.length - 1) {
    currentIndex++;
    loadQuestion();
  } else {
    finish();
  }
}

function finish() {
  textFinish.innerHTML = `Você acertou ${questionsCorrect} de ${questions.length}`;
  content.style.display = "none";
  contentFinish.style.display = "flex";
}

function loadQuestion() {
  spnQtd.innerHTML = `${currentIndex + 1}/${questions.length}`;
  const item = questions[currentIndex];
  answers.innerHTML = "";
  question.innerHTML = item.question;

  // Embaralhar as respostas
  const shuffledAnswers = shuffleArray(item.answers);

  shuffledAnswers.forEach((answer) => {
    const div = document.createElement("div");

    div.innerHTML = `
    <button class="answer" data-correct="${answer.correct}">
      ${answer.option}
    </button>
    `;

    answers.appendChild(div);
  });

  document.querySelectorAll(".answer").forEach((item) => {
    item.addEventListener("click", nextQuestion);
  });
}

// Função para embaralhar um array (Fisher-Yates Shuffle)
function shuffleArray(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}

// Função para selecionar aleatoriamente 10 perguntas
function selectRandomQuestions(perguntas, quantidade) {
  return shuffleArray(perguntas).slice(0, quantidade);
}

// Função para buscar perguntas e respostas da API
function fetchQuestionsAndAnswers() {
  let categoriaQuiz = 'SQL';

  if (window.location.pathname.includes('games.html')) {
    categoriaQuiz = 'Games';
  } else if (window.location.pathname.includes('programacao.html')) {
    categoriaQuiz = 'Programação';
  }

  Promise.all([
    fetch('http://localhost:8080/api/perguntas').then(response => response.json()),
    fetch('http://localhost:8080/api/respostas').then(response => response.json())
  ])
  .then(([perguntas, respostas]) => {
    console.log('Perguntas:', perguntas);
    console.log('Respostas:', respostas);

    const perguntasFiltradas = perguntas.filter(pergunta => pergunta && pergunta.categoria === categoriaQuiz);
    console.log('Perguntas Filtradas:', perguntasFiltradas);

    // Seleciona aleatoriamente 10 perguntas, garantindo que sejam distintas
    const perguntasSelecionadas = selectRandomQuestions(perguntasFiltradas, 10);
    console.log('Perguntas Selecionadas:', perguntasSelecionadas);

    questions = perguntasSelecionadas.map(pergunta => {
      if (!pergunta || !pergunta.id) {
        console.error('Pergunta inválida:', pergunta);
        return { question: '', answers: [] };
      }

      const respostasFiltradas = respostas.filter(resposta => resposta.pergunta && resposta.pergunta.id === pergunta.id);
      return {
        question: pergunta.texto,
        answers: respostasFiltradas.map(resposta => ({
          option: resposta.texto,
          correct: resposta.correta
        }))
      };
    });

    loadQuestion();
  })
  .catch(error => console.error('Erro ao carregar o quiz:', error));
}

document.addEventListener('DOMContentLoaded', fetchQuestionsAndAnswers);

document.getElementById('voltar').addEventListener('click', function() {
  window.location.href = 'index.html';
});
