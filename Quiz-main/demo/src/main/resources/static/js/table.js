function fetchAndDisplayData(url, containerSelector) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const container = document.querySelector(containerSelector);
            container.innerHTML = '';
            data.forEach(row => {
                const rowElement = document.createElement('div');
                rowElement.textContent = JSON.stringify(row);
                container.appendChild(rowElement);
            });
        })
        .catch(error => console.error('Erro ao buscar dados:', error));
}

// Exemplo de uso
document.addEventListener('DOMContentLoaded', () => {
    // Ajuste as URLs conforme necessário
    fetchAndDisplayData('http://localhost:8080/api/jogadores/pontuacao-maior-que/25', '#jogadores-pontuacao');
    fetchAndDisplayData('http://localhost:8080/api/perguntas/contagem-por-categoria', '#contagem-perguntas');
    fetchAndDisplayData('http://localhost:8080/api/jogadores/quizzes-jogados', '#jogadores-quizzes');
    fetchAndDisplayData('http://localhost:8080/api/perguntas/perguntas-e-respostas', '#perguntas-respostas');
    fetchAndDisplayData('http://localhost:8080/api/jogadores/jogadores-quizzes-perguntas', '#jogadores-quizzes-perguntas');
    fetchAndDisplayData('http://localhost:8080/api/quizzes/quizzes-perguntas-respostas-certas', '#quizzes-perguntas-respostas');
    fetchAndDisplayData('http://localhost:8080/api/jogadores/total-quizzes-jogados', '#total-quizzes-jogados');
    fetchAndDisplayData('http://localhost:8080/api/quizzes/pontuacao-media', '#pontuacao-media');
    fetchAndDisplayData('http://localhost:8080/api/jogadores/datas-primeiro-ultimo-quiz', '#datas-primeiro-ultimo-quiz');
});