#!/bin/sh
mvn clean package
docker build -t linxianer12/eventservice:latest . 
docker push linxianer12/eventservice:latest 
