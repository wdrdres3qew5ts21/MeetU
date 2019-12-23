1) Elasticsearch นั้นไม่สามารถ Remapping fields ที่เคยสร้างไปแล้วดังนั้นจึงมีความจำเป็นที่ copy ข้อมูล
จาก Index เดิมไปที่ Index ใหม่และเก็บเป็น index ชั่วคราวไว้คือ meetu.events_backup
POST _reindex
{
  "source": {
    "index": "meetu.events"
  },
  "dest": {
    "index": "meetu.events_backup" 
  }
}

2)เมื่อเรามี index ชั่วคราวคือ meetu.events_backup แล้วเราก็จะทำการลบ index เดิม meetu.events ซึ่งไม่สามารถใช้งานได้
เพราะอย่างที่ได้กล่าวไป elasticsearch ไม่สามารถแก้ไข fields ที่ทำการ Mapping ไปแล้ว
DELETE /meetu.events

*** ต้องผ่านขั้นตอน 1) และ 2) ตามลำดับ  จากนั้นจึงเริ่มขั้นตอนที่ 3)
3) ก่อนที่เราจะทำการใช้ logstash parsing ข้อมูลจาก index meetu.events_backup ไปยัง index เดิมคือ meetu.events
ให้เราทำการสร้าง analyzer ประจำ index meetu.events ขึ้นมาใหม่และทำการใช้ request ที่ 4)
สร้างตัว Analyzer ไว้เป็นฐานในการค้นหาคำก่อน
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

** ต้องทำขั้นตอนที่ 1) 2) 3) และขั้นตอนที่ 4) ให้สำเร็จจากนั้นค่อยใช้ logstash parsing ข้อมูลเป็นขั้นสุดท้าย 
4) นำ Analyzer ที่เรา custom ขึ้นมาเองชื่อว่า thai_analyzer มาใช้กับ  index ของเราเพื่อให้ Elasticticsearch มี 
Analyzer ประจำ field ของตนและพร้อมที่จะทำการนำข้อมูลที่ backup ไว้จาก index meetu.events_backup กลับมาเข้าสู่
index meetu.events ซึ่งเป็น index จริงๆที่เราต้องการใช้นั่นเอง 

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
