PUT /my_index/
DELETE /my_index
GET /my_index

POST /my_index/_mapping
{
  "properties":{
    "detail":{
      "type": "text",
      "analyzer": "rebuilt_thai"
    }
  }
}

PUT /my_index
{
  "settings": {
    "analysis": {
      "filter": {
        "thai_stop": {
          "type":       "stop",
          "stopwords":  "_thai_" 
        }
      },
      "analyzer": {
        "rebuilt_thai": {
          "tokenizer":  "thai",
          "filter": [
            "lowercase",
            "decimal_digit",
            "thai_stop"
          ]
        }
      }
    }
  }
}


GET /my_index/_search
{
  "query": {
    "match": {
      "detail": "japan"
    }
  }  
}

POST /my_index/_doc/1
{
  "detail": "Japan Expo กิจกรรมสุดสนุกพบกับเหล่า Cosplay ชื่อดัง"
}


POST /my_index/_analyze
{
  "analyzer": "rebuilt_thai", 
  "text": "Japan Expo กิจกรรมสุดสนุกพบกับเหล่า Cosplay ชื่อดัง"
}