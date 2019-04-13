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

POST _reindex
{
  "source": {
    "index": "my_index"
  },
  "dest": {
    "index": "my_index_new"
  }
}

PUT /my_index
{
  "settings": {
    "analysis": {
      "filter": {
        "lnwza": {
          "type":       "stop",
          "stopwords":  "_thai_" 
        }
      },
      "analyzer": {
        "eiei": {
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

POST /my_index/_close

GET /my_index/_search
{
  "query": {
    "match": {
      "detail": "กิจกรรมไทยๆ"
    }
  }  
}

POST /my_index/_doc/1
{
  "detail": "Japan Expo กิจกรรมสุดสนุกพบกับเหล่า Cosplay ชื่อดัง",
  "price": 1490
}

POST /my_index/_doc/2
{
  "detail": "ร่วมสัมผัสบรรยากาศแบบไทยๆและภูมิปัญญาท้องถิ่นด้วยราคาถูกไม่แพงและย่อมเยา"
}



POST /my_index/_analyze
{
  "analyzer": "rebuilt_thai", 
  "text": "Japan Expo กิจกรรมสุดสนุกพบกับเหล่า Cosplay ชื่อดัง"
}


PUT /my_index_new
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
        "my_analyzer": {
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