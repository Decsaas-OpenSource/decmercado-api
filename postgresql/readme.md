# Docker postgresql

## Passos para o uso da imagem.

- Criar um arquivo .env
    - Utilize o arquivo default.env como base para criar seu arquivo .env

> [!NOTE]
> Usuário e senha usado nesse arquivo, deve ser trocado nos arquivos de configuração no projeto **config-server**
> - application-postgresql.yaml

- Executando o docker na raiz do projeto postgresql
```
docker run -d -p 5432:5432 --env-file .env --name postgresql postgres:alpine3.20
```
