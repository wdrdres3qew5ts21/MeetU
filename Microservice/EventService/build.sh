#!/bin/sh
docker build -t linxianer12/eventservice . 
docker push linxianer12/eventservice 
docker run -p 3002:3002 linxianer12/eventservice  