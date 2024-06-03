# Docker mongoDB

## Passos para o uso da imagem.

- Criar um arquivo .env
    - Utilize o arquivo default.env como base para criar seu arquivo .env

> [!NOTE]
> Usuário e senha usado nesse arquivo, deve ser trocado nos arquivos de configuração no projeto **config-server**
> - application-mongodb.yaml
> - application-security.yaml

- Executando o docker na raiz do projeto mongodb
```
docker run -d -p 27017:27017 --env-file .env --name mongodb
```

#### Passo a passo para adicionar usuário.

- docker exec -it <nome_do_conteiner_ou_id> bash
- mongosh --username <username> --password <password>
- use login-service
```
db.users.insert({
    nome: <defina_um_nome>,
    senha : <defina_uma_senha>,
    conta_expirada : false,
    conta_bloqueada : false,
    credenciais_expiradas : false,
    habilitado : true
})
```

> [!NOTE]
> Para definir sua senha use a classe JwtEncoder

# Algumas dicas para uso do mongo

- Acessar o docker
```
docker exec -it <nome_do_conteiner_ou_id> bash
```
- Acessar a base do mongo no docker
```
mongosh --username <username> --password <password>
```
- Listar bancos de dados
```
show dbs
```
- Selecionar um banco de dados
```
use <database>
```
- Listar coleções
```
show collections
```
- Visualizar documentos em uma coleção específica
```
db.<colecao>.find().pretty()
```