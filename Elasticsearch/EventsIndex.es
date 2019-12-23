GET _cluster/health
GET /events
DELETE /events
1) สร้างตัว Analyzer ไว้เป้นฐานก่อนและจากนั้นก็นำตัว Analyzer ไปใช้งานกับ Index นั่นเอง
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

// Analyzer แบบเพิ่มความละเอียดในการแก้ grammar
PUT /events
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
        },
        "filter_shingle": {
          "type": "shingle",
          "max_shingle_size": 3
        }
        
      },
      "analyzer": {
        "thai_analyzer": {
          "tokenizer":  "thai",
          "filter": [
            "lowercase",
            "decimal_digit",
            "thai_stop",
            "en_stemmer",
            "filter_shingle"
          ]
        }
      }
    }
  }
}


2) สร้าง Mapping ให้ type ใช้งานได้ตามที่ตั้งเอาไว้สิ่งที่ต้องทำได้แก่การจัดการการค้นหาของรายละเอียดกิจกรรม
จึงต้องทำเป็น Full Text Search รวมถึงชื่อกิจกรรมด้วยเช่นกันลองสังเกตที่ตรง fields นั้นจะเป้นการระบุเพิ่มให้
field หนึ่งปกติของเราเช่น field eventName มีประเภทเป้นทั้งประเภทหลักคือ text สำหรับการทำ full text Search
แต่ในขณะเดียวกันก็ระบุลักษณะเพิ่มเลยเติม s แสดงความหลากหลายให้สามารถเป้นประเภท keyword ได้ด้วยด้วย object 
ชื่อ keyword เราเป็นคนตั้งขึ้นมาเองนั่นเองและ ข้างใน object ค่อยเป้น type ตามปกติเหมือนเดิม
The eventName field can be used for full text search.
The eventName.keyword field can be used for sorting and aggregations
refs: www.elastic.co/guide/en/elasticsearch/reference/current/multi-fields.html

POST /events/_mapping
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


GET /events/_search
{
  "query": {
        "bool" : {
            "must" : {
                "match_all" : {}
            },
            "filter" : {
                "geo_distance": {
                    "distance" : "200km",
                    "location.geopoint" : {
                        "lat" : 12.2,
                        "lon" : 13.42
                    }
                }
            }
        }
    }
}


GET /events/_search
{
  "query": {
        "bool" : {
            "must" : {
                "match_all" : {}
            },
            "filter" : {
                "geo_distance": {
                    "distance" : "200km",
                    "location.geopoint" : {
                        "lat" : 13.6519739,
                        "lon" : 100.492686
                    }
                }
            }
        }
    },
    "sort": [
        {
          "_geo_distance": {
            "location.geopoint": { 
              "lat":  13.6519739,
              "lon": 100.492686
            },
            "order":         "asc",
            "unit":          "km", 
            "distance_type": "arc" 
          }
        }
    ]
}

https://www.elastic.co/guide/en/elasticsearch/guide/current/sorting-by-distance.html#scoring-by-distance

GET /events/_search
{
  "query": {
    "match_all": {}
  }
}



Filter คือช่วงที่อยู่ในการถามที่เป้นแค่ใช้กับไม่เท่านั้นจึงต้องเป้นพวก range หรือ term queryนั่นเองซึ่งจะมี must_not หรือ should 
ด้วยเช่นกันใช้ในการเพิ่มแต้มคะแนนเวลาค้นหา
GET /events/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "eventDetail": "ไทยๆ"
          }
        }
      ],
      "filter": {
          "terms": {
            "eventTags": [
              "anime"
            ]
          }
      }
    }
  }
}


GET /events/_search
{
  "query": {
    "range": {
      "createEventDate": {
        "gte": "2015-01-01"
      }
    }
  }
}

การค้นหาแบบ multifields และมีการเพิ่มคะแนนให้กับ rank ตัวนั้นๆเช่นการค้นหาว่าสงกรานต์ในเชียงใหม่
GET /events/_search
{
  "query": {
    "query_string": {
      "fields" : ["eventDetail^2", "location^5"],
      "query" : "Songkarn holiday in Chaing Mai"
    }
  }
}


POST /events/_open
POST /events/_close

GET /events/_search
{
  "query": {
    "match": {
      "eventDetail": "ไทยๆ"
    }
  }  
}

GET /events/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "eventDetail": "Songkarn in Bangkok"
          }
        }
      ],
      "filter": {
          "terms": {
            "eventTags": [
              "Culture"
            ]
          }
      }
    }
  }
}

GET /events/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "createEventDate": {
        "order": "desc"
      }
    }
  ],
  "from": 0, "size": 10
}

POST /events/_analyze
{
  "analyzer": "thai_analyzer", 
  "text": "ร่วมสัมผัสบรรยากาศแบบไทยๆและภูมิปัญญาท้องถิ่นด้วยราคาถูกไม่แพงและย่อมเยา"
}

Dumerau Lavenshtein Distance ทำให้ค้นหาคำโดดแบบพิมพ์ผิดได้เช่น songkarn จากคำว่า songkran
GET /events/_search
{
  "query": {
    "query_string": {
      "fields" : ["eventName","eventDetail", "location.*^3"],
        "query": "songkarn~"
    }
  }
}


