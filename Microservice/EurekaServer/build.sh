#!/bin/sh
mvn clean package
docker build -t linxianer12/eurekaserver:latest . 
docker push linxianer12/eurekaserver:latest 
