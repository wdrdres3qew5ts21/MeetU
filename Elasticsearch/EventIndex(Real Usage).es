1) สร้างตัว Analyzer ไว้เป็นฐานในการค้นหาคำก่อน
PUT /meetu.events
{
  "settings": {
    "analysis": {
      "filter": {
        "thai_stop": {
          "type":       "stop",
          "stopwords":  "_thai_" 
        },
        "en_stemmer" : {
          "type" : "stemmer",
          "name" : "english"
        }
      },
      "analyzer": {
        "thai_analyzer": {
          "tokenizer":  "thai",
          "filter": [
            "lowercase",
            "decimal_digit",
            "thai_stop",
            "en_stemmer"
          ]
        }
      }
    }
  }
}

2) นำ Analyzer ที่เรา custom ขึ้นมาเองชื่อว่า thai_analyzer มาใช้กับ  index ของเรา

POST /meetu.events/_mapping
{
  "properties":{
    "eventName":{
      "type": "text",
      "analyzer": "thai_analyzer",
      "fields" : {
        "keyword" : {
        "type" : "keyword",
        "ignore_above" : 256
        }
      }
    },
    "eventTags": { 
      "type":     "text",
      "fielddata": true
    },
    "eventDetail":{
      "type": "text",
      "analyzer": "thai_analyzer"
    },
    "createEventDate":{
      "type": "date"
    },
    "endRegisterDate":{
      "type": "date"
    },
    "eventEndDate":{
      "type": "date"
    },
    "eventStartDate":{
      "type": "date"
    },
    "location.geopoint": {
      "type": "geo_point"
    }

  }
}




POST /meetu.events/_mapping
{
  "properties":{
    "eventName":{
      "type": "text",
      "analyzer": "thai_analyzer",
      "fields" : {
        "keyword" : {
        "type" : "keyword",
        "ignore_above" : 256
        }
      }
    },
    "eventTags": { 
      "type":     "text",
      "fielddata": true
    },
    "eventDetail":{
      "type": "text",
      "analyzer": "thai_analyzer"
    },
    "createEventDate":{
      "type": "date"
    },
    "endRegisterDate":{
      "type": "date"
    },
    "eventEndDate":{
      "type": "date"
    },
    "eventStartDate":{
      "type": "date"
    },
    "location.geopoint": {
      "type": "geo_point"
    }

  }
}
