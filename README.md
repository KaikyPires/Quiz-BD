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
- **Outras Dependências**: FontAwesome para ícones, Axios para requisições HTTP

## Como Executar

### Pré-requisitos

- Java 17+
- Maven
- MySQL
- Node.js (se for usar ferramentas de front-end com Node)

### Passos para rodar o projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seuusuario/quiz-app.git
   cd quiz-app

 
 ## Configure o Banco de Dados

1. Crie um banco de dados MySQL chamado `quiz`.
2. Execute o script SQL `schema.sql` (incluído no projeto) para criar as tabelas.

## Configuração do Back-end

1. No arquivo `application.properties`, configure as credenciais do seu banco de dados:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/quiz
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha

  ## Imagens do projeto
  ![Captura de tela 2024-09-09 203847](https://github.com/user-attachments/assets/a7609953-de85-4a57-89cd-c54ea7fb3c33)
![Captura de tela 2024-09-09 203859](https://github.com/user-attachments/assets/9eb3c0cd-702c-4dd5-b1c8-8e1b008886b3)

![Captura de tela 2024-09-09 203915](https://github.com/user-attachments/assets/b1b7a0bb-209a-4177-86f3-e456fbcc9c6f)
![Captura de tela 2024-09-09 203933](https://github.com/user-attachments/assets/1c19c632-13ce-4c59-a82a-052ae7da8006)
