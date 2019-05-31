# hadoop-twitter
Análise do dataset do Twitter usando o Hadoop

Features
---
1. Faz a contagem das tags de arquivos texto de uma pasta do twitter

Roteiro de execução
---
Faça a configuração do Cluster do Hadoop com 3 nós usando containers Docker disponível em https://github.com/topicos-sistemas-distribuidos/hadoop-cluster-docker

1. Vá até o nó master do hadoop. 

Obs: Inicie o hdfs e o yarn. É preciso garantir que o nó master tenha instalado o git, o maven e um editor de texto.  

2. Faça o clone desse repositório no nó master.
```
$ git clone https://github.com/topicos-sistemas-distribuidos/hadoop-twitter.git
```

3. Execute o script de acordo com a feature compilada
```
$./my-periodoDia.sh -turnos
$./my-postagemDia.s -dias
```

Obs: Os scripts precisam de permissão para executar. Por exemplo: chmod +x my-periodoDia.sh

Para a execução do my-periodoDia.sh - O output deverá ser uma lista contendo as tags e quantas vezes ela apareceu nas postagens por turno.

Para a execução do my-postagemDia.sh - O output deverá ser uma lista contendo as tags e quantas vezes ela apareceu no dia.
