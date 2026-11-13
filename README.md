# Biblioteca em Mãos

Aplicativo Android acadêmico para gerenciamento de uma biblioteca pessoal. O usuário pode visualizar livros, cadastrar novas obras, consultar todos os dados de um livro e compartilhar a descrição do projeto pela tela Sobre/Ajuda.

## Identificação dos autores

- **Aluno(a):** PREENCHER COM O NOME COMPLETO.
- **Segundo integrante (se houver):** NÃO SE APLICA / PREENCHER.

> Antes da entrega ao professor, substitua os campos acima e o texto `PREENCHER COM O NOME DO(A) ALUNO(A)` em `SobreScreen.kt` pelo(s) nome(s) correto(s).

## Tecnologias

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Uma única `MainActivity`
- Estado em memória usando `mutableStateListOf`

## Como abrir e executar

1. Instale uma versão do Android Studio compatível com Android Gradle Plugin 9.1.1.
2. Tenha o **JDK 17** configurado no Android Studio.
3. No Android Studio, escolha **Open** e selecione a pasta raiz `BibliotecaEmMaos` (a pasta que contém `settings.gradle.kts`).
4. Aguarde a sincronização do Gradle. Os scripts `gradlew` e `gradlew.bat` iniciam o Gradle 9.3.1; na primeira utilização, é necessário ter acesso à internet para baixar a distribuição e as dependências.
5. Pelo **SDK Manager**, instale a plataforma Android SDK 37 e as ferramentas necessárias para executar um emulador. O projeto compila com `compileSdk 37` e tem `minSdk 24`.
6. Inicie um emulador Android (API 24 ou superior) ou conecte um dispositivo com a depuração USB habilitada.
7. Selecione a configuração `app` e pressione **Run**.

## Funcionalidades implementadas

- **Listagem:** exibe três livros iniciais, título, autor, ano, gênero e quantidade de páginas; mostra a quantidade de registros e oferece acesso ao cadastro e à tela Sobre/Ajuda.
- **Cadastro:** possui título, autor, ano, gênero, número de páginas e resumo. Título e autor são obrigatórios; ano deve estar entre 1000 e 2100; número de páginas deve ser um inteiro positivo. Dados inválidos exibem mensagens e não são salvos.
- **Identificador:** cada livro cadastrado recebe um UUID automaticamente.
- **Detalhes:** a rota recebe o ID do livro como argumento, pesquisa na coleção compartilhada e exibe todos os campos. Um ID desconhecido apresenta uma mensagem em vez de encerrar o aplicativo.
- **Navegação:** quatro destinos registrados no `NavHost`. As telas Cadastro, Detalhes e Sobre/Ajuda possuem botão visível **Voltar** na barra superior, além das ações de retorno no conteúdo. A tela inicial é a raiz da pilha e não apresenta botão de retorno interno.
- **Rolagem:** a listagem da tela inicial utiliza `LazyColumn`, apropriada para uma coleção de livros. O formulário, os detalhes de um único livro e a tela Sobre/Ajuda usam rolagem vertical comum, pois apresentam conteúdo único em vez de uma lista de itens. O cadastro e a tela Sobre/Ajuda retornam com `popBackStack`, preservando o fluxo de retorno à lista.
- **Compartilhamento:** o botão da tela Sobre/Ajuda abre o seletor do Android usando `Intent.ACTION_SEND` com conteúdo `text/plain`; uma `ActivityNotFoundException` é tratada com uma mensagem.
- **Estado:** a mesma instância de `BibliotecaStore` é criada acima do `NavHost` e compartilhada entre as telas. A lista é observável pelo Compose e atualiza a interface quando um livro é incluído.

## Organização do projeto

```text
app/src/main/java/br/com/estudos/biblioteca/
├── MainActivity.kt
├── data/
│   └── BibliotecaStore.kt
├── model/
│   └── Livro.kt
├── navigation/
│   ├── AppNavigation.kt
│   └── Routes.kt
├── screens/
│   ├── CadastroScreen.kt
│   ├── DetalhesScreen.kt
│   ├── HomeScreen.kt
│   └── SobreScreen.kt
└── ui/theme/
    └── BibliotecaTheme.kt
```

- `MainActivity`: ponto de entrada Android e chamada de `setContent`.
- `AppNavigation` e `Routes`: controlador, destinos, rotas e argumento de ID; também conecta os callbacks das telas ao armazenamento e à navegação.
- `Livro`: modelo de dados com identificador único e informações da obra.
- `BibliotecaStore`: coleção compartilhada e operações de inclusão e busca por ID.
- `screens`: interface de cada um dos quatro destinos.
- `BibliotecaTheme`: cores do Material 3.

## Roteiro de testes manuais

Execute os testes no emulador ou dispositivo e marque cada caixa após confirmar o resultado:

- [ ] Abrir um dos três livros iniciais, conferir os detalhes e voltar à lista.
- [ ] Tentar salvar o cadastro com título ou autor vazio e conferir a mensagem de validação.
- [ ] Informar ano inválido ou páginas igual a zero e confirmar que o cadastro é bloqueado.
- [ ] Cadastrar um livro válido e confirmar que ele aparece imediatamente na lista.
- [ ] Abrir o livro recém-cadastrado e conferir todos os dados digitados.
- [ ] Cancelar um cadastro e confirmar que nenhum novo livro foi adicionado.
- [ ] Acessar Sobre/Ajuda, conferir finalidade/instruções/autor e acionar Compartilhar aplicativo.
- [ ] Voltar repetidamente e confirmar que a navegação retorna às telas anteriores sem duplicar a tela inicial.

**Situação desta entrega:** os arquivos-fonte e a configuração do projeto foram preparados, mas o aplicativo ainda precisa ser sincronizado, compilado e executado no Android Studio com Android SDK instalado. Portanto, os testes acima não estão declarados como executados.

## Declaração de uso de inteligência artificial

Foi utilizado **ChatGPT (OpenAI)** como apoio para planejar a arquitetura, gerar uma primeira versão do código Kotlin/Jetpack Compose, estruturar a navegação, implementar validações e elaborar este README. A solução foi organizada em arquivos separados para modelo, estado, navegação, telas e tema. O(a) aluno(a) deve revisar o código, substituir sua identificação, executar os testes no Android Studio e realizar os ajustes necessários antes da entrega, garantindo que consiga explicar e modificar cada parte durante a entrevista.

## Limitações conhecidas

- Os livros ficam armazenados em memória enquanto o estado da aplicação existir. Não há banco de dados e os novos cadastros não são garantidos após o encerramento completo do processo.
- Os nomes dos autores ainda são campos de preenchimento e devem ser atualizados antes da publicação no GitHub.
- O funcionamento final depende da sincronização e execução do projeto em um ambiente Android Studio com o SDK exigido.
