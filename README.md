# Desafio Backend - Requisitos

## 1. Validações

Você deve ajustar as entidades (model e sql) de acordo com as regras abaixo: 

- `Product.name` é obrigatório, não pode ser vazio e deve ter no máximo 100 caracteres.
- `Product.description` é opcional e pode ter no máximo 255 caracteres.
- `Product.price` é obrigatório deve ser > 0.
- `Product.status` é obrigatório.
- `Product.category` é obrigatório.
- `Category.name` deve ter no máximo 100 caracteres.
- `Category.description` é opcional e pode ter no máximo 255 caracteres.

## 2. Otimização de Performance
- Analisar consultas para identificar possíveis gargalos.
- Utilizar índices e restrições de unicidade quando necessário.
- Implementar paginação nos endpoints para garantir a escala conforme o volume de dados crescer.
- Utilizar cache com `Redis` para o endpoint `/auth/context`, garantindo que a invalidação seja feita em caso de alteração dos dados.

## 3. Logging
- Registrar logs em arquivos utilizando um formato estruturado (ex.: JSON).
- Implementar níveis de log: DEBUG, INFO, WARNING, ERROR, CRITICAL.
- Utilizar logging assíncrono.
- Definir estratégias de retenção e compressão dos logs.

## 4. Refatoração
- Atualizar a entidade `Product`:
  - Alterar o atributo `code` para o tipo inteiro.
- Versionamento da API:
  - Manter o endpoint atual (v1) em `/api/products` com os códigos iniciados por `PROD-`.
  - Criar uma nova versão (v2) em `/api/v2/products` onde `code` é inteiro.

## 5. Integração com Swagger
- Documentar todos os endpoints com:
  - Descrições detalhadas.
  - Exemplos de JSON para requisições e respostas.
  - Listagem de códigos HTTP e mensagens de erro.

## 6. Autenticação e Gerenciamento de Usuários
- Criar a tabela `users` com as colunas:
  - `id` (chave primária com incremento automático)
  - `name` (obrigatório)
  - `email` (obrigatório, único e com formato válido)
  - `password` (obrigatório)
  - `role` (obrigatório e com valores permitidos: `admin` ou `user`)
- Inserir um usuário admin inicial:
  - Email: `contato@simplesdental.com`
  - Password: `KMbT%5wT*R!46i@@YHqx`
- Endpoints:
  - `POST /auth/login` - Realiza login.
  - `POST /auth/register` - Registra novos usuários (se permitido).
  - `GET /auth/context` - Retorna `id`, `email` e `role` do usuário autenticado.
  - `PUT /users/password` - Atualiza a senha do usuário autenticado.

## 7. Permissões e Controle de Acesso
- Usuários com `role` admin podem criar, alterar, consultar e excluir produtos, categorias e outros usuários.
- Usuários com `role` user podem:
  - Consultar produtos e categorias.
  - Atualizar apenas sua própria senha.
  - Não acessar ou alterar dados de outros usuários.

## 8. Testes
- Desenvolver testes unitários para os módulos de autenticação, autorização e operações CRUD.

---

# Perguntas

1. **Se tivesse a oportunidade de criar o projeto do zero ou refatorar o projeto atual, qual arquitetura você utilizaria e por quê?**
   R: Eu iria implementar conceitos de SOLID e Clean Architecture, utilizando do padrão gateway e useCases, hoje em dia mesmo em projetos pequenos, ter uma arquitetura bem estrutura facilita em muito manutenção e ajuda muito na escalabilidade,
   além do fator desacopalhamento, para os casos de que seja necessário adicionar um banco NoSql por exemplo, e assim seria muito fácil lidar com inclusão e remoção de depedência de frameworks.
2. **Qual é a melhor estratégia para garantir a escalabilidade do código mantendo o projeto organizado?**
   R:Uma documentação bem elaborada e um code review critíco são sempre os melhores caminhos, tendo uma documentação formada de como o projeto deve seguir, seguindo o padrão estabelicido que ja é visado pela escalabilidade,
   o código se mantém estável e organizado. É interessante também ter uma esteira de CI/CD para efetuar as pipelines de compilação e testes da aplicação para ter sempre um código revisado não só por um ser humano, mas por um software em si.
3. **Quais estratégias poderiam ser utilizadas para implementar multitenancy no projeto?**
   R: O melhor caminho para se criar um multitenancy, seria uma separação estratégica dos bancos para cada tipo de contexto. Exemplo: uma aplicação que lida com usuários que possuem diferentes bancos, e separam por clientId, o melhor caminho seria
   separar os bancos, e no próprio ter algum tipo de Singleton que gerenciasse as multitenancy que seria quem iria auxiliar na hora de saber para qual banco consultar.
4. **Como garantir a resiliência e alta disponibilidade da API durante picos de tráfego e falhas de componentes?**
   R: Melhor de garantir estabilidade, seria implementar Load Balancer na aplicação, juntando de Circuit Breaker para garantir que caso ocorra, um número maior de usuários, aumente as instâncias de aplicação rodando, e caso ocorra alguma queda, o
   CircuitBreaker seria responsável por garantir um fallback, além também de que caso esteja sendo utilizado mensageria, é possível implementar uma fila de mensagens mortas para reprocessar as requisições que falharam.
5. **Quais práticas de segurança essenciais você implementaria para prevenir vulnerabilidades como injeção de SQL e XSS?**
   R: Principais práticas, seria a integridade dos dados recebidos do front, tanto em questão de tamanho recebeido, formato e meio de recebimento, utilizando de validações no próprio corpo da requisição ou Url da mesma.
6. **Qual a abordagem mais eficaz para estruturar o tratamento de exceções de negócio, garantindo um fluxo contínuo desde sua ocorrência até o retorno da API?**
   R: Sempre entender que todo método pode dar erro, visando uma tratativa pronta para o mesmo, exemplo: Um método de efetuar pagamento, inúmeras coisas podem acontecer, mas se acaso o serviço cair ou o método falhar, é possível criar meio de salvar o pagamento
   e efetuar o processamento do mesmo posteriormente, e enviar uma mensagem clara e sucinta ao usuário. Tudo vai depender do contexto da sua aplicação, porém, o ideal é sempre ter uma tratativa mesmo que genérica para que o usuário não fique sem um status do 
   ocorrido, além de sempre criar mensagem de exceção claras para quando o desenvolvedor for trabalhar em cima do erro, poder entender o fluxo do serviço e o motivo pelo qual o mesmo possa ter dado problema.
7. **Considerando uma aplicação composta por múltiplos serviços, quais componentes você considera essenciais para assegurar sua robustez e eficiência?**
   R: Ter uma arquitetura de Kubernetes devidamente configurada para lidar com o contexto de microserviços, tendo clusters prontos para caso uma aplicação caia, outra seja iniciada garantido que o serviço fique somente momentaneamente parado.
   Além de sempre saber distribuir corretamente memória para as aplicações que realmente possam exigir mais.
8. **Como você estruturaria uma pipeline de CI/CD para automação de testes e deploy, assegurando entregas contínuas e confiáveis?**
   R: O ponta pé inicial seria configurar a pipeline de CI assegurando a compilação do projeto, rodando os testes unitários criados, garantindo que tanto a aplicação quanto os próprios testes estejam ok, após isso, escanear o código fonte com um revisor de 
   códigos, por exemplo o próprio SonarCloud, criando regras de validação para garantir que o código não seja enviado com potenciais itens de perigo no próprio código. Na parte de CD, seria feito um versionamento da própria aplicação, garantindo um backup e 
   histórico da mesma em caso de necessidade, trabalhando sempre com uma aplicação dockerizada ao enviar para produção. 

Obs: Forneça apenas respostas textuais; não é necessário implementar as perguntas acima.

