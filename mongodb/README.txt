alterar extensão para .md depois da correção do bug no intellij

# Criar arquivo .env
Utilize o arquivo default.env para criar seu arquivo .env

# Executando o docker
docker run -d -p 27017:27017 --env-file .env --name mongodb

# Acessar o docker
docker exec -it <nome_do_conteiner_ou_id> bash

# Acessar a base do mongo no docker
mongosh --username <username> --password <password>

# Listar bancos de dados
show dbs

# Selecionar um banco de dados
use <database>

# Listar coleções
show collections

# Visualizar documentos em uma coleção específica
db.<colecao>.find().pretty()
