# Docker rabbitmq

## Passos para o uso da imagem.

- Criar um arquivo .env
    - Utilize o arquivo default.env como base para criar seu arquivo .env

> [!NOTE]
> Usuário e senha usado nesse arquivo, deve ser trocado nos arquivos de configuração no projeto **config-server**
> - application-rabbitmq.yaml

- Executando o docker na raiz do projeto rabbitmq
```
docker run -d -p 5672:5672 -p 15672:15672 --env-file .env --name rabbitmq rabbitmq:3.13.3-management-alpine
```
