#!/bin/bash
while true
do
  curl  --fail -u elastic:PleaseChangeMe \
    "http://localhost:9200/_cluster/health?wait_for_status=yellow" \
    && break
  sleep 5
done

curl -X POST -v -k "http://localhost:9200/_security/user/kibana/_password?pretty" -u elastic:PleaseChangeMe   -H 'Content-Type: application/json' -d ' { "password" : "PleaseChangeMe" } '  && echo "success change password success"
    
  