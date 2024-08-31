document.addEventListener('DOMContentLoaded', () => {
    const resultMessage = document.getElementById('resultMessage');
    const playerNameInput = document.getElementById('playerName');
    const submitResultsButton = document.getElementById('submitResults');

    // Supondo que você armazene o número de acertos em uma variável global ou local
    const questionsCorrect = /* obtenha o número de acertos aqui */

    // Atualize a mensagem de resultado
    resultMessage.textContent = `Você acertou ${questionsCorrect} de 10 perguntas`;

    submitResultsButton.addEventListener('click', () => {
        const playerName = playerNameInput.value;

        if (playerName.trim() === '') {
            alert('Por favor, digite seu nome.');
            return;
        }

        // Enviar o nome do jogador e a quantidade de acertos para o backend
        fetch('http://127.0.0.1:8080/api/jogadores', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nome: playerName,
                // Note que a entidade Jogador não tem um campo 'acertos', então não estamos enviando isso.
                // Caso você queira adicionar um campo 'acertos', você deve atualizar a entidade e o banco de dados.
            }),
        })
        .then(response => response.json())
        .then(data => {
            alert('Resultados enviados com sucesso!');
            window.location.href = 'escolha.html';
        })
        .catch(error => {
            console.error('Erro ao enviar resultados:', error);
        });
    });
});
