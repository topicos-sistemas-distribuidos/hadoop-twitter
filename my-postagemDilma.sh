#!/bin/bash

# test the hadoop cluster by running wordcount
mvn "-Dmyproperty=TwitterPostagensDilma" clean && mvn compile "-Dmyproperty=TwitterPostagensDilma" && mvn package "-Dmyproperty=TwitterPostagensDilma"

# create input files 
mkdir input$1
echo "My Twitter da Dilma - Try $1"
cp /root/hadoop-twitter/dataset/* input$1

# create input directory on HDFS
hadoop fs -mkdir -p input$1

# put input files to HDFS
hdfs dfs -put ./input$1/* input$1

# run q2
hadoop jar /root/hadoop-twitter/target/q2-0.0.1.jar input$1 output$1

# print the output of sort
echo -e "\nTwitter da Dilma output:"
hdfs dfs -cat output$1/part-r-00000