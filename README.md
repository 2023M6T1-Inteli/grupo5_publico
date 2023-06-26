![](./img/inteli-logo.png) 

# Introdução

Este é um dos repositórios do projeto de alunos do Inteli em parceria com uma grande indústria de papel nacional no 1º semestre de 2023. Este projeto foi desenvolvido por alunos do Módulo 6 do curso de Ciência da Computação.

# Projeto: *Solução de otimização de corte de bobinas de papel*

# Grupo: *Waste Cutters*

# Alunos

* *Felipe Sampaio*
* *Jonas Sales*
* *Luiz Granville*
* *Mateus Almeida*
* *Pedro Munhoz*
* *Sergio Lucas*
* *Thomas Barton*
* *Vinicius Santos*

# Descrição

Diversas aplicações na indústria de produção de papel têm como objetivo cortar rolos jumbo em bobinas menores, de acordo com as necessidades dos clientes. A empresa em questão enfrenta o desafio do Problema de Corte de Estoque, que consiste em encontrar a melhor combinação de cortes e bobinas, minimizando custos e desperdícios. Esse problema, que foi abordado academicamente por Kantorovich em 1939, requer a consideração de restrições como capacidade da cortadeira, número máximo de facas e especificações de largura das bobinas. O grupo desenvolveu um algoritmo de otimização baseado em técnicas de programação linear (método Simplex), para alcançar uma solução ótima e eficiente.

O algoritmo em desenvolvimento busca atender às demandas específicas da empresa parceira, proporcionando benefícios como redução de desperdício de material, economia de tempo e aumento da produtividade. Inspirados em estudos anteriores, como o trabalho de Gilmore e Gomory em 1961, que explorou o método de geração de colunas para solucionar o CSP, o grupo está adaptando essas abordagens para obter uma solução matematicamente embasada e computacionalmente eficiente. O algoritmo leva em consideração variáveis como múltiplas tiradas e a minimização de sobras de material. Com uma restrição de tempo de execução máxima de 30 segundos, a solução proposta visa otimizar o processo de corte de estoque e melhorar a alocação de recursos na empresa.

Ao final do projeto, a performance do algoritmo desenvolvido será comparada com a solução atualmente implementada pela empresa. Essa análise permitirá avaliar a eficiência e os benefícios da solução proposta, contribuindo para aprimorar as operações de corte de estoque da empresa. Com uma abordagem fundamentada em pesquisa operacional e programação linear, o grupo visa fornecer uma ferramenta confiável e eficiente para maximizar a utilização de recursos, reduzir custos e aprimorar sua produção de embalagens de papel.

# Guia de instalação

- [PostgreSQL]('https://www.postgresql.org/download/'), recomendamos instalá-lo a partir do site oficial: PostgreSQL. Durante a instalação, pode haver uma opção específica que você precisa desabilitar, pois ela configura o banco de dados para executar o projeto de forma específica. Além disso, ao configurar o PostgreSQL, será necessário definir uma senha de acesso ao banco de dados.
  No projeto em questão, o grupo utilizou uma senha padrão que pode ser encontrada no seguinte caminho: [especificar o caminho onde a senha padrão está localizada]. Essa senha é necessária para acessar o banco de dados e todas as informações relacionadas ao acesso ao banco de dados devem estar disponíveis, como o nome do banco de dados, o nome de usuário e a porta utilizada.
  Certifique-se de configurar corretamente as informações de acesso ao PostgreSQL para que o projeto possa se conectar ao banco de dados sem problemas.

```
codigo\backend\planejador\src\main\resources\application.properties
```

<br>

- [Spring Boot](https://spring.io/) - O Spring Boot é resposável por executar as rotas, ou seja toda comunicação com o banco de dados, back-end e a interface de visualização.

<br>

- [JSON](https://mvnrepository.com/artifact/org.json/json) - É o formato em que os arquivos são enviados na comunicação entre frontend, backend banco de dados e algoritmo.

<br>

- [React.js](https://react-cn.github.io/react/downloads.html) - Framework em que a interface web é executado.

<br>
 
 ### 5. Variáveis de ambiente
 
 Para garantir o correto funcionamento da aplicação, pode ser necessário configurar as variáveis de ambiente durante a instalação de algumas bibliotecas ou frameworks. Cada biblioteca ou framework possui suas próprias instruções de configuração, que devem ser seguidas para garantir o funcionamento adequado.

Durante a instalação de uma biblioteca ou framework, é comum que sejam fornecidas orientações sobre como definir as variáveis de ambiente necessárias. Essas variáveis podem incluir informações como caminhos de diretório, chaves de API, configurações específicas do banco de dados, entre outras.

É importante ler e seguir as instruções fornecidas pela documentação da biblioteca ou framework em questão para garantir uma configuração correta e adequada das variáveis de ambiente. Isso contribui para o funcionamento efetivo da aplicação e evita possíveis erros ou comportamentos inesperados.

<br>

### 6. Execução Geral do projeto

<br>

#### 6.1 Execução do Banco de dados

Após a instalação do banco de dados e a configuração adequada no arquivo `application.properties`, você deve navegar até a pasta onde está localizado o arquivo e executar a classe principal (main) desse arquivo em questão.

Normalmente, a classe principal possui um método main que é o ponto de entrada da aplicação. Ao executar essa classe, a aplicação será iniciada e poderá se conectar ao banco de dados configurado.

Certifique-se de ter todas as dependências e bibliotecas necessárias instaladas e configuradas corretamente antes de executar a classe principal. Isso garantirá que a aplicação possa acessar o banco de dados e funcionar adequadamente.

```
codigo\backend\planejador\src\main\java\inteli\cc6\planejador\PlanejadorApplication.java
```

<br>

#### 6.2 Execução do Front-end

Após toda configuração de setup de ambiente feita (instalação da IDE, bibliotecas, etc), é o momento de executar a interface, para isso abra o terminal do vs code e digite o seguinte comando

```
 cd codigo\frontend\my-app
```

Ao fazer isso, acessou a pasta my-app e basta apenas digitar o seguinte comando para baixar todas as dependências:

```
npm install
```

Na sequência, para carregar a interface e ser direcionado a ela:

```
 npm start
```

Pronto, a interface está sendo executada localmente na localhost:3000

_Obs: é preciso seguir essas três etapas para executar a solução por completo._

<br>

# Licença

Este projeto utiliza a [licença Apache 2.0](LICENSE).
