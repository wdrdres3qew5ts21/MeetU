#!/bin/bash
curl -X POST -v -k --cacert /usr/share/elasticsearch/config/certificates/ca/ca.crt "https://elasticsearch1:9200/_security/user/kibana/_password?pretty"  -u elastic:   -H 'Content-Type: application/json' -d' { "password" : "" } '

if [[ $? == 51 ]]
then
  echo "Connect but it not secure So fail to change Password"
else
  echo "Skip SSL Secure Connection by -k argument so Change password Success"
fi

echo "======="
echo "Variable two sign : $$?"
echo "Variable single sign: $?"

#!/bin/bash

curl -k https://elasticsearch1:9200

echo "Yeah $?"
