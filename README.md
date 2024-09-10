# Quiz App

Este projeto é um aplicativo de quiz desenvolvido com **Spring** no backend e uma interface web para exibir perguntas e respostas. O quiz possui três categorias: **Programação**, **SQL** e **Jogos**, com perguntas aleatórias e respostas embaralhadas para uma experiência dinâmica.

## Funcionalidades

- Três tipos de quizzes: Programação, SQL e Jogos.
- Cada quiz contém 10 perguntas com 4 opções de resposta.
- As respostas são embaralhadas aleatoriamente para evitar padrões.
- Os jogadores podem registrar seu nome e suas pontuações são armazenadas em um banco de dados.
- Se um jogador já estiver registrado, sua nova pontuação é somada à pontuação existente.
- Pontuações máximas e mínimas são exibidas após o quiz.
- Sistema de banco de dados para armazenar quizzes jogados e pontuações dos jogadores.

## Tecnologias Utilizadas

- **Back-end**: Spring Boot, Java
- **Front-end**: HTML, CSS, JavaScript
- **Banco de Dados**: MySQL

## Como Executar

### Pré-requisitos

- Java 17+
- Maven
- MySQL
2. **Configure o Banco de Dados**:
   - Crie um banco de dados MySQL chamado `quiz`.
   - Execute o script SQL `schema.sql` (incluído no projeto) para criar as tabelas.

3. **Configuração do Back-end**:
   - No arquivo `application.properties`, configure as credenciais do seu banco de dados:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/quiz
     spring.datasource.username=seu-usuario
     spring.datasource.password=sua-senha
     ```

4. **Execute o Back-end**:
   - Compile e execute o servidor Spring Boot:
     ```bash
     mvn spring-boot:run
     ```

5. **Front-end**:
   - Abra os arquivos `sql.html`, `games.html` ou `programacao.html` diretamente no navegador para testar as diferentes categorias do quiz.

## Rotas Disponíveis no Back-end

- `GET /api/quizzes`: Retorna os quizzes disponíveis.
- `POST /api/jogadores`: Cria um novo jogador ou atualiza a pontuação se o nome já existir.
- `GET /api/jogadores`: Lista os jogadores com suas pontuações.

## Personalizações

- Para adicionar novas perguntas ao quiz, edite o arquivo `quizzes.sql` no banco de dados e adicione mais questões e categorias.
- As respostas são embaralhadas dinamicamente para cada quiz. Isso pode ser ajustado no front-end, se necessário.

## Imagens do Projeto

![Descrição da Imagem 1](https://github.com/user-attachments/assets/2db6fda3-9325-491c-9219-6b0cbbd816b0)
*Tela inicial do aplicativo, onde o usuário pode iniciar o quiz.*

![Descrição da Imagem 2](https://github.com/user-attachments/assets/4092c5ae-5f1e-4ba9-b694-92635035363d)
*Tela para seleção de categoria do quiz, onde o usuário escolhe entre Programação, SQL e Jogos.*

![Descrição da Imagem 3](https://github.com/user-attachments/assets/1a486e97-9269-408f-9b00-bba351e96f38)
*Adicione uma legenda ou explicação para a imagem aqui.*

![Descrição da Imagem 3](https://github.com/user-attachments/assets/1807816d-12d5-4e65-89a1-05e0397230d7)
*Interface mostrando diferentes tabelas com base na seleção do usuário.*

![Descrição da Imagem 3](https://github.com/user-attachments/assets/25d37a43-e2ef-44f9-bc76-57d39aa9e478)
*Tela de ranking exibindo as pontuações dos jogadores.*

## Licença![Captura de tela 2024-09-09 203915]()




Este projeto está licenciado sob a [MIT License](LICENSE).
