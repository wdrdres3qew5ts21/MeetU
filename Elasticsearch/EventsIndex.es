GET _cluster/health
GET /events
DELETE /events
1) สรา้งตัว Analyzer ไว้เป้นฐานก่อนและจากนั้นก็นำตัว Analyzer ไปใช้งานกับ Index นั่นเอง
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
    "eventDetail":{
      "type": "text",
      "analyzer": "thai_analyzer",
      "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
      }
    }
  }
}

GET /events/_search
{
  "query": {
    "match_all": {}
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

