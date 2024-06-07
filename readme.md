# Aplicativo Decmercado API

A ideia desse projeto, é mostrar são as funcionalidades de alguns componentes do ecossistema do Spring Cloud de forma prática.

> [!WARNING]
> Está sendo usado o Java 22 nos projetos

## Funcionalidades Principais

Algumas tecnologias usadas.

- Spring Cloud Config Server
- Spring Cloud Eureka Server
- Spring Cloud Gateway
- Spring Cloud Security
- Spring Cloud Web
- Spring Data MongoDB
- Spring Data JPA
- Spring Boot AMPQ
- Docker MongoDB
- Docker Postgresql
- Docker RabbitMQ
- Flyway (Postgresql)

## Configurando o Spring Cloud Security

- Deve se criar um par de chaves para serem usados no JWT
- Adicionar os arquivos **.pub** e **.key** na pasta resources do projeto *gateway*
- [Para criar o par de chaves](https://github.com/Decsaas-OpenSource/decmercado-api/tree/main/gateway/readme.md)

## Configuração de usuário no MongoDB

Será necessário criar a base (login-service) e adicionar um usuário para as operações. ([Passo a passo para inserir o usuário](https://github.com/Decsaas-OpenSource/decmercado-api/tree/main/mongodb/readme.md)).

> [!NOTE]
> Após a criação do usuário, é possivel usar todos os endpoints da aplicação.
> Toda a operação deve ser a partir do gateway, dado que é uma arquitetura de microservices.

## Efetuar login

Após a criação do usuário, efetue o login no seguinte endpoint:

```
http://localhost:8000/login 
```
### body:
```
{
    "login": seu_usuario_criado,
    "password": sua_senha_criado
}
```

## Gateway Endpoint

Exemplos de uso dos endpoints a partir do Gateway
```
http://localhost:8000/produto/v1/editar
...
http://localhost:8000/minha-lista/v1/inserir
```

## Documentação

Para mais detalhes dos outros endpoints, acesse a documentação no seguintes url.

### Produtos
```
localhost:8300/documentacao
```

### Minhas listas

Só existe um endpoint
```
POST http://localhost:8000/minha-lista/v1/inserir
```
*body*
```
{
     "productId": "",
     "productDescription": "",
     "comment": "",
     "amount": "",
     "selected": false
}
```


