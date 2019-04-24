PUT /events
DELETE /events
GET /kibana_sample_data_flights/_search
{
  "query": {
    "match_all": {}
  }
}

GET /kibana_sample_data_flights/_search
{
  "query": {
        "bool" : {
            "must" : {
                "match_all" : {}
            },
            "filter" : {
                "geo_distance": {
                    "distance" : "200km",
                    "OriginLocation" : {
                        "lat" : -33.96480179,
                        "lon" : 18.60169983
                    }
                }
            }
        }
    }
}




POST /events/_mapping
{
  "properties":{
    "eventDetail":{
      "type": "text",
      "analyzer": "thai_analyzer"
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

PUT /events
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
        "thai_analyzer": {
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

POST /events/_open

GET /events/_search
{
  "query": {
    "match": {
      "eventDetail": "ไทยๆ"
    }
  }  
}

POST /events/_doc/1
{
  "eventDetail": "Japan Expo กิจกรรมสุดสนุกพบกับเหล่า Cosplay ชื่อดัง",
  "price": 1490
}

POST /events/_doc/2
{
  "eventDetail": "ร่วมสัมผัสบรรยากาศแบบไทยๆและภูมิปัญญาท้องถิ่นด้วยราคาถูกไม่แพงและย่อมเยา"
}



POST /events/_analyze
{
  "analyzer": "thai_analyzer", 
  "text": "ร่วมสัมผัสบรรยากาศแบบไทยๆและภูมิปัญญาท้องถิ่นด้วยราคาถูกไม่แพงและย่อมเยา"
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