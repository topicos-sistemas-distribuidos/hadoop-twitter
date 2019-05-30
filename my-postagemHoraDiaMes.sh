#!/bin/bash

# test the hadoop cluster by running wordcount
mvn "-Dmyproperty=PostagemHoraDiaMes" clean && mvn compile "-Dmyproperty=PostagemHoraDiaMes" && mvn package "-Dmyproperty=PostagemHoraDiaMes"

# create input files 
#mkdir input$1
#echo "My sort - Try $1"
#cp /root/words/books/* input$1

# create input directory on HDFS
#hadoop fs -mkdir -p input$1

# put input files to HDFS
#hdfs dfs -put ./input$1/* input$1

# run q2
#java jar /root/q2/target/q2-0.0.1.jar input$1 output$1
java -jar target/q2-0.0.1.jar

# print the output of sort
#echo -e "\nsort output:"
#hdfs dfs -cat output$1/part-r-00000
