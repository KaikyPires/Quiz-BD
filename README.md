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
*Adicione uma legenda ou explicação para a imagem aqui.*


![Descrição da Imagem 2](caminho/para/sua-imagem2.png)
*Adicione uma legenda ou explicação para a imagem aqui.*

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
