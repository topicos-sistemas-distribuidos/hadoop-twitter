# hadoop-twitter
Análise do dataset do Twitter usando o Hadoop.

O Apache Hadoop é uma framework open-source de componentes que usam uma rede de vários computadores para resolver problemas de Big Data via distribuição de tarefas em um cluster de computadores usando o modelo de Programação MapReduce para distribuir a execução de processos em diversos nós do Cluster criado. 

![License](https://img.shields.io/badge/License-BSD%202--Clause-orange.svg) 

Features
---
1. Faz a contagem das tags de arquivos texto de uma pasta do twitter

a) Faz a extração das tags mais citadas em um período (manhã, tarde, noite) do dia

b) Faz a extração das tags mais citadas durante um dia

c) Faz a extração das tags mais citada em uma determinada hora do dia

d) Faz a extração das postagens sobre a Dilma no período das eleições 2014

e) Faz a extração das postagens sobre o Aecio no período das eleições 2014

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
$./my-postagemDia.sh -dias
$./my-postagemHoraDiaMes.sh -porhora
$./my-postagemDilma.sh -dilma
$./my-postagemAecio.sh -aecio
```

Obs: Os scripts precisam de permissão para executar. Por exemplo: chmod +x my-periodoDia.sh

Para a execução do my-periodoDia.sh - O output deverá ser uma lista contendo as tags e quantas vezes ela apareceu nas postagens por turno.

Para a execução do my-postagemDia.sh - O output deverá ser uma lista contendo as tags e quantas vezes ela apareceu no dia.

Para a execução do my-postagemHoraDiaMes.sh - O output deverá ser uma lista contendo os twitters por hora em um dado dia.

Para a execução do my-postagemDilma.sh - O output deverá ser uma lista com o conteudo dos twitters que falaram na Dilma.

Para a execução do my-postagemAecio.sh - O output deverá ser uma lista com o conteudo dos twitters que falaram na Aecio.
