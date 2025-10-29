# SpringStruct

## Spring Struct - CLI para Estrutura de Projetos Spring Boot
### Uma ferramenta de linha de comando simples para criar automaticamente a estrutura de pacotes em projetos Spring Boot.

## Como Usar
**Com Java:**
Entre no diretório do seu projeto Spring Boot e execute:
- cd seu-projeto-spring/src/main/java
- java -jar spring-struct.jar com.empresa.meuprojeto

**Com Docker:**
Entre no diretório do seu projeto Spring Boot e execute:
- cd seu-projeto-spring/src/main/java
- docker run -v $(pwd):/app spring-struct com.empresa.meuprojeto

**Estrutura Criada**
Após executar o comando, será criada a seguinte estrutura no seu projeto:
- controller/ - Controladores REST
- service/ - Lógica de negócio
- repository/ - Acesso a dados
- model/ - Entidades JPA
- dto/ - Objetos de transferência
- config/ - Configurações
- exception/ - Tratamento de exceções

**Para Desenvolvedores**
Build do Projeto:
Execute mvn clean package para compilar o projeto.

**Testar Localmente:**
Use java -jar target/spring-struct.jar com.exemplo.teste para testar.

**Docker Build:**
Execute docker build -t spring-struct . para criar a imagem Docker.

## Exemplo Completo de Uso
- Crie um novo projeto Spring Boot

- Entre no diretório src/main/java do projeto

- Execute o comando com seu pacote base

- Pronto! Todos os pacotes serão criados automaticamente

- Economize tempo - não crie pacotes manualmente!
