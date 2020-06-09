#!/bin/bash
# while true
# do
#   curl  --fail -k -u elastic:PleaseChangeMe \
#     "https://localhost:9200/_cluster/health?wait_for_status=yellow" &&  break
#   sleep 5
# done

while true
do
  curl  --fail -k -u elastic:PleaseChangeMe \
    curl -X POST -v -k  "https://localhost:9200/_security/user/kibana/_password?pretty"  -u elastic:PleaseChangeMe   -H 'Content-Type: application/json' -d ' { "password" : "PleaseChangeMe" } ' &&  break
  sleep 5
done

curl -X POST -v -k  "https://localhost:9200/_security/user/kibana/_password?pretty"  -u elastic:PleaseChangeMe   -H 'Content-Type: application/json' -d ' { "password" : "PleaseChangeMe" } '
