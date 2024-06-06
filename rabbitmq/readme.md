# Docker rabbitmq

## Passos para o uso da imagem.

- Executando o docker na raiz do projeto rabbitmq
```
docker run -d -p 5672:5672 -p 15672:15672 --env-file .env --name rabbitmq rabbitmq:3.13.3-management-alpine
```
