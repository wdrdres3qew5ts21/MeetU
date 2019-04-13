PUT /my_index/
DELETE /my_index
GET /my_index

POST /my_index/_mapping
{
  "properties":{
    "detail":{
      "type": "text",
      "analyzer": "my_custom_analyzer"
    }
  }
}

PUT /my_index
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_custom_analyzer": {
          "type": "custom", 
          "tokenizer": "thai"
        }
      }
    }
  }
}

GET /my_index/_search
{
  "query": {
    "term": {
      "detail": "สนุก"
    }
  }  
}

POST /my_index/_doc/1
{
  "detail": "กิจกรรมสุดสนุก"
}


POST /my_index/_analyze
{
  "analyzer": "my_custom_analyzer", 
  "text": "กิจกรรมสุดสนุก"
}