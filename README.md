![alt text](https://seniorproject.sit.kmutt.ac.th/projectPoster/posterIT59-BU37.jpg)

## Cloud Native Microservices: Event Management Platform (Elasticsearch Stack & Spring Cloud) 

MeetU: Platform Event Management  ที่เข้ามามีส่วนช่วยเหล่าวัยรุ่นไฟแรงที่มีพลังสร้างสรรค์เป็นช่วงเวลาแห่งการทำกิจกรรมได้มาสร้างกิจกรรมและทำกิจกรรมดีๆร่วมกัน โดย MeetU มีจุดเด่นในเรื่องของการค้นหากิจกรรมตามที่พวกคุณชื่นชอบได้ด้วยระบบแนะนำกิจกรรม หรือจะเป็นการไปค้นพบกิจกรรมแนวใหม่ยอดนิยมที่ทำให้คุณได้หลุดออกมาจาก comfort zone เรียนรู้กิจกรรมใหม่ๆก็ได้ และสิ่งที่มาช่วยเป็นตัวขับเคลื่อนให้กิจกรรมสนุกขึ้นนั้นก็คือระบบ Leader Board ที่สามารถให้คุณแสดงคะแนนกิจกรรมจากกิจกรรมที่คุณเคยไปเข้าร่วมได้ พร้อมกับพบปะผู้คนที่มีความสนใจเหมือนกัน ! สำหรับในด้าน Technology เอง MeetU มาพร้อมกับสถาปัตยกรรม Application แบบ Microservices ซึ่งมามีส่วนช่วยในการออกแบบอย่าง Module ในอนาคตนั้นสามารถทำได้ง่ายได้ ซึ่งสำหรับ Core Feature ที่เราเลือก Technology มาใช้นั่นก็คือ Elasticsearch Stack ที่ทำหน้าที่ทั้งในส่วนร่วมกับ Backend คือระบบการค้นหาแบบ Full Text Search ช่วยให้เราค้นหากิจกรรมต่างๆได้ง่ายขึ้น และการ Monitoring Infeastructure ของระบบ Microservices และสำหรับภาษาที่เลิอกใช้ก็คือ Java Spring boot ที่เป็น Framework การพัฒนา Backend ซึ่งมาควบคู่กับหลัก 12 Factors อันนำไปสู่การเป็น Cloud Native Application ให้สามารถ Scaling ได้ในรูปแบบ Horizontal รวมไปถึงการรองรับ Fault Torelant ได้ในระดับนึงเพราะ Application เราถูกแบ่งออกเป็น Services หลักๆ ต่อให้มี Services ใดเกิดการล่มลงไป Application ก็ยังคงมีบาง Service ที่รองรับและทำงานได้ต่อไป

## Diagram System Flow
![alt text](https://i.ibb.co/y5cr7sC/image.png)

## วิธีการติดตั้ง project
###### ติดตั้ง MongoDB Database เชื่อมเข้ากับ Elasticsearch
1. Restore ข้อมูลกลับเข้า MongoDB
ทำการ import ไฟล์จาก meetu-mongodb-dump ด้วยการใช้คำสั่ง mongorestore https://docs.mongodb.com/manual/tutorial/backup-and-restore-tools/ เพื่อนำข้อมูลที่ถูก dump ออกมาเป้น bson กลับเข้าไปใน mongodb

2. Migrate ข้อมูลจาก MongoDB ไปยัง Elasticsearch
ต้องใช้ Monstache ซึ่งเป็น Golang Library ในการ Import ข้อมูลจาก MongoDB ให้เข้าไปใน Elasticsearch ซึ่งผู้ใช้ไม่จำเป็นต้องลง Golang ที่เครื่องก็ได้เพราะว่า Monstache ได้ compile ออกมาเป็น execute file สำหรับ OS ทั้งสาม Platform คือ Darwin, Window และ Linux เรียบร้อยแล้ว 
![alt text]( https://i.ibb.co/7VH6q8d/image.png)
https://rwynn.github.io/monstache-site/start/ เมื่อเข้าไปที่เว็บของ Monstache แล้วให้เราโหลด execute binary มาจาก github ผ่าน https://github.com/rwynn/monstache/releases หรือจะใช้ execute bin ที่เป็นของ window จาก MeetU Repository นี้ก็ได้โดยเข้าไปที่ folder monstache-golang-window และใช้คำสั่ง  monstache.exe -f .\mongo-elastic.toml  เพื่อเป็นการสั่งให้ทำการ execute monstache bin และใช้ไฟล์ config ตาม path ที่ระบุ
![alt text](https://i.ibb.co/TwT1jZX/image.png)
สำหรับตัวอย่างไฟล์ config จะมีดังนี้โดยให้ปรับ url ของ MongoDB และ Elasticsearch เป็นของที่เราใช้ 

** ใช้ไฟล์ที่ Elasticsearch/EventIndex(Real Usage).es 
3. Backup Index บน Elasticsearch และเปลี่ยนเป็นชื่อใหม่เพื่อใช้การ Remapping Index
จากข้อ 2. Migrate ข้อมูลจาก MongoDB ไปยัง Elasticsearch ที่เราสามารถนำข้อมูลกลับเข้าไปใน Elasticsearch แล้วก้จริงแต่ว่าข้อมูลที่ Migrate จาก MongoDB เข้าไปใน Elasticsearch นั้นจะมี format หลายจุดที่ผิดเพราะบาง fields เก็บข้อมูลไม่ถูกต้องนั่นก็คือ fields geopoint รวมไปถึง fields elasticEventId ซึ่งเป็น key ที่ใช้ในการ reference ข้อมูลระหว่าง MongoDB กับ Elasticsearch ก็ขาดไปดังนั้นเราจึงต้องทำการ Remapping Index บน Elasticsearch ให้เพิ่ม fields เหล่านั้นและเพิ่ม  Analyzer ของ Elasticsearch เข้าไปเพราะถ้าเราไม่ทำ Analyzer ประจำ fields แล้วคุณสมบัติของการทำ Full Text Search ก็จะไม่สามารถทำได้อย่างเต็มประสิทธิภาพ
![alt text](https://i.ibb.co/kgSgZ5j/image.png)
```
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
```
2)เมื่อเรามี index ชั่วคราวคือ meetu.events_backup แล้วเราก็จะทำการลบ index เดิม meetu.events ซึ่งไม่สามารถใช้งานได้
เพราะอย่างที่ได้กล่าวไป elasticsearch ไม่สามารถแก้ไข fields ที่ทำการ Mapping ไปแล้ว
แต่ Elasticsearch นั้นไม่สามารถ Remapping fields ที่เคยสร้างไปแล้วดังนั้นจึงมีความจำเป็นที่ copy ข้อมูลจาก Index เดิมไปที่ Index ใหม่และเก็บเป็น index ชั่วคราวไว้ก่อนดั่งภาพ  
และหลังจากที่เราทำงาน Backup ข้อมูลเอาไว้แล้วเราจะทำการลบ index เดิมที่ใช้งานไมไ่ด้เพราะขาด analyzer นั่นก็คือ index meetu.events 
```
DELETE /meetu.events
```

4.สร้าง Analyzer และ Mapping Type สำหรับ Elasticsearch index 
หลังจากที่ทำมีข้อมูลที่ Sync จาก MongoDB เข้าไปใน Elasticsearch เรียบร้อยแล้วเราจะพบว่ามีบาง fields เก็บข้อมูลไม่ถูกต้องนั่นก็คือ fields geopoint 
และเราก็ยังขาด Analyzer ของ Elasticsearch เพราะถ้าเราไม่ทำ Analyzer ประจำ fields แล้วคุณสมบัติของการทำ Full Text Search ก็จะไม่สามารถถูกใช้งานได้อย่างมีประสิทธิภาพ

![alt text](https://i.ibb.co/MMhN16p/image.png)

```
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

```

 ดังนั้นให้เราทำการเข้าไปที่ folder Elasticsearch และเปิดไฟล์ EventIndex(Real Usage).es ขึ้นมาด้วยโปรแกรม text editor  ใดๆก็ตามจากนั้นนำ request ข้อ 1) ซึ่งใช้ในการสร้าง Analyzer ที่ index meetu.events ไปใช้งานบน Kibana ดั่งภาพและเมื่อสำเร็จดั่งภาพแล้วให้ทำการนำ request ข้อ 2) คือการสร้าง mapping บน index meetu.events ให้นำ Analyzer  ที่สร้างไปก่อนหน้ามาใช้งานบน fields eventName, eventDetail ทำให้เราสามารถทำ Full Text Search ได้นั่นเอง
 
![alt text](https://i.ibb.co/GHYmQ3M/image.png)

```

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

```

5. ทำการ Parsing ข้อมูลจาก Index Backup มาสู่ Index ที่ใช้งานจริงด้วย Logstash
ในขั้นตอนนี้ต้องหมายความว่าเราทำการ Backup ข้อมูลไปไว้ที่ index meetu.events_backup เรียบร้อยและเรามี index meetu.events ตัวใหม่ที่มีการสร้าง analyzer เรียบร้อยแล้วเท่านั้นจึงจะสามารถใช้ logstash parsing ข้อมูลจาก index meetu.events_backup ส่งกลับเข้าไปใน index meetu.events ซึ่งจะมีการทำ mapping type เรียบร้อยแล้วทำให้หลังจากขั้นตอน logstash นี้ Elasticsearch ที่ index meetu.events จะเป้น index ที่พร้อมใช้งานนั่นเอง

โดยให้เราใช้ logstash อ่านไฟล์ config นี้ที่ location logstash/meetu-geopoint.conf ตัวอย่างคำสั่งเช่น bin/logstash -f logstash/meetu-geopoint.conf
https://www.elastic.co/guide/en/logstash/current/advanced-pipeline.html
```
# โครงสร้างของ logstash จะประกอบไปด้วยสามส่วนด้ยกันนั้นคือ input, filter และ output ตามลำดับ
# ซึ่งความหมายของ input ก็คือประเภทของการรับข้อมูลที่เรารับเข้ามาเช่นประเภท csv หรือไฟล์ใดๆเช่น txt, log ที่เป็น text base
# โดยเราจะต้องเข้าไปดูที่ document ของ logstash ว่ามีรูปแบบการรับข้อมูลแบบใดบ้างนั่นเอง https://www.elastic.co/guide/en/logstash/current/index.html
# ซึ่ง logstash นั้นจะไม่ได้ออกแบบมาให้เก็บข้อมูลแบบ real-time จะเน้นการทำหน้าที่ ETL ข้อมูลเป็นหลักเช่นการแปลง format ของข้อมูล

# filter คือตัวที่ใช้ในการ ETL Format ข้อมูลหลังจากที่ได้ทำการรับข้อมูลมาแล้วก็จะทำการแแปลงข้อมูลตาม format ที่มีไว้ให้
# โดยเราสามารถลองอ่านข้อมูลเพิ่มเติมได้จาก https://www.elastic.co/guide/en/logstash/current/filter-plugins.html
input{
        elasticsearch {
        hosts => "http://meetu-milktea.tk:9200" # แก้ host ที่เป็น input ให้ถูกต้องหากใช้บน localhost ก็ให้ใช้ localhost
        index => "meetu.events_backup" # index ที่จะทำการดึงข้อมูลมาใช้ parsing
        query => '{ "query": { "match_all": { } } }' # จะดึงข้อมูลจำนวนเท่าใดด้วยผ่าน query ของ elasticsearch
         
      }
}
filter {

        elasticsearch {
            hosts => "http://meetu-milktea.tk:9200" # ต้องเหมือนกับ input
            index => "meetu.events_backup" # ต้องเหมือนกับ input

        }

        mutate{
            add_field => {
                "latTempt" => "%{[location][geopoint][latitude]}"
                "lonTempt" => "%{[location][geopoint][longitude]}"
            }
        }

        mutate {
            remove_field => [ "[location][geopoint]"]
        }

        mutate {
            remove_field => [ "[organize][geopoint]"]
        }

        mutate {
            convert => {
            "latTempt" => "float"
            "lonTempt" => "float"
            }
        }

        mutate {
            add_field => {
                "[location][geopoint][lat]" => "%{[latTempt]}"
            }

            add_field => {
                "[location][geopoint][lon]" => "%{[lonTempt]}"
            }
        }

        mutate{
            remove_field => [ "latTempt", "lonTempt", "[organize][_id]", "reviewList" ]
        }


}
# output เป็นการระบุว่าไฟล์ของเรานั้นต้องส่งออกไปยังที่ใดโดยที่นี้คือเป้นการส่งไปเก็บเลยที่ elasticsearch นั้นเอง 
output {

   elasticsearch { 
       # host คือตำแหน่งของที่ ip:port ที่จะไปบันทึก
       hosts => ["http://meetu-milktea.tk:9200"] 
       # คือ index ของ elasticsearch ที่จะทำการบันทึกข้อมูลลงไป
       index => "meetu.events"
       document_id => "%{[elasticEventId]}"

    }

    # standard output ที่จะส่งข้อมูลออกมาผ่าน terminal ซึ่ง encode เป็นภาษา ruby 
   stdout { codec => rubydebug }
}
```

###### วิธีการติดตั้ง Microservices ของ Spring boot
1. Config Server จัดการ Credential
ด้วยแนวคิดของ Microservice ซึ่งโปรเจคนี้ใช้ Spring Cloud Config เข้ามาจัดการ Credential ของระบบ
https://www.baeldung.com/spring-cloud-configuration
![alt text](https://i0.wp.com/blog.leekyoungil.com/wp-content/uploads/2017/04/1.png)
ซึ่ง Service ทุกตัวได้แก่ API Gateway, User Service, Event Service และ Community Service จะต้องเรียกใช้ config ผ่าน
Config Server ดั่งภาพเท่านั้นซึ่งเราจะต้องทำการสร้าง Private Repo เอาไว้สำหรับการเก็บ Credential ซึ่งเราจะเลือกใช้หรือไม่เลืออกใช้ก็ได้ถ้าหากไม่ต้องการเลือกใช้
ให้ทำการ comment code ในส่วนของ spring cloud
``````
pom.xml
ให้ทำการ comment dependency นี้ออกไปถ้าหากเราไม่ต้องการใช้ config server
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```````
แต่ถ้าต้องการใช้ก็ให้ทำการสร้าง private git repository ขึ้นมาก่อนแล้วใช้การ config เชื่อมไปยัง private repository ดั่งภาพหรือจะทำเป็น private key แทนเพื่อความปลอดภัยก็ได้เช่นกัน
![alt text](https://i.ibb.co/c1cBSF1/image.png)

2. ลำดับการทำงานจะต้องสั่งให้ Config Server ทำงานก่อนจากนั้นตามด้วย Eureka Server ซึ่งทำหน้าที่เป็น Service Register และหลังจากนั้นจะสามารถสั่งให้ 
API Gateway, User Service, Event Service และ Community Service ตัวใดตัวหนึ่งทำงานก่อนกันก็ได้เพราะว่าทั้ง 4 Service นี้นั้นไม่เกี่ยวข้องกัน  สิ่งที่จำเป็น
มีแค่การเริ่มทำงานของ Config Server เป็นอันดับแรกเพราะว่าเป็นตัวบันทึก Credential ถ้าหากไม่ทำงานก่อนแล้ว Service อื่นเข้ามาต่อใช้งานก็จะไม่มี Credential 
ให้ใช้งานนั่นเอง  ส่วน Service Register เป็นลำดับที่สองเพราะว่าการที่แต่ล่ะ Service จะรู้จักกันได้นั้นจำเป็นต้องมี Service Register แต่ว่า Service ที่เหลือมี Service Discovery ประจำตัวเองอยู๋แล้วดังนั้นการ Register จะเกิดขึ้นอัตโนมัติ
![alt text](https://i.ibb.co/MfBy0dy/image.png)
ซึ่งการ Config ทุกอย่างนั้นจะอยู่ใน  bootstap.yml, application.properties และ elasticapm.properties  


###### วิธีการติดตั้ง Elasticsearch APM / Metric Beat 
สำหรับการทำ log ภายใน Microservice นั้นถือเป็นสิ่งที่สำคัญเพราะถ้าหากว่ามี Service ใดเกิดการ Failure ลงไปแล้วเราไม่มีการทำ Log จะทำให้วินิจฉัยได้ยากว่าต้องปรับปรุงอะไรในระบบ  เพราะด้วยการที่ Microservice จะมี Traffic ของ Network ภายในจำนวนมากรวมไปถึงการเรียกใช้ API ต่างๆที่กระจายออกไปผ่าน Gateway แต่เนื่องด้วย Platform ของเรานั้นใช้ Elasticsearch เป็นฐานข้อมูลดั่งที่ได้กล่าวไปในข้างต้นอยู่แล้ว
เราจึงตัดสินใจเลือกใช้ Eco System ของ Elastic Stack นั่นก็คือการใช้ Application Performance Monitoring (APM) ควบคู่กับ Kibana ซึ่งเป็น Dashboard สำหรับใช้ในการ Visualization ข้อมูล Log ที่เกิดขึ้นในระบบได้อย่าง Realtime ทั้งการเก็บ JVM Utilization และ API Usage เช่นเดียวกัน โดยหลักการทำงานของ APM นั้นจะเป็นการนำ Agent ลงไปใส่ใน Spring Boot Application ของเราผ่าน Dependency บน Maven และสั่งให้ทำการส่ง Log ในช่วงเวลาที่ระบุไว้  จากการทดลองพบว่าการส่ง log ทุกๆช่วง 5 นาทีเป็นช่วงเวลาที่ไม่กินพื้นที่มากเกินไปและสามารถนำ log ดั่งกล่าวไปวินิจฉัยได้
APM: https://www.elastic.co/guide/en/apm/get-started/current/index.html
Metricbeats: https://www.elastic.co/guide/en/beats/metricbeat/current/metricbeat-module-system.html
``````
docker run  -d\
  --network=meetu_elastic\
  --user=root \
  --volume="$(pwd)/metricbeat.docker.yml:/usr/share/metricbeat/metricbeat.yml:ro" \
  --volume="/var/run/docker.sock:/var/run/docker.sock:ro" \
  --volume="/sys/fs/cgroup:/hostfs/sys/fs/cgroup:ro" \
  --volume="/proc:/hostfs/proc:ro" \
  --volume="/:/hostfs:ro" \
  docker.elastic.co/beats/metricbeat:7.2.1 metricbeat -e \
  -E output.elasticsearch.hosts=["elasticsearch1:9200"] 


docker run -d \
  --network=meetu_elastic\
  -p 8200:8200\
  --user=apm-server \
  --volume="$(pwd)/apm-server.docker.yml:/usr/share/apm-server/apm-server.yml:ro" \
  docker.elastic.co/apm/apm-server:7.4.0 \
  --strict.perms=false -e \
  -E output.elasticsearch.hosts=["elasticsearch1:9200"]  
  
```````
![alt text](https://i.ibb.co/6yzGQfg/image.png)

## Tools & Services

![alt text](https://seniorproject.sit.kmutt.ac.th/screenshot/screenshot10.IT59-BU37.jpg)

Backend:
- Microservices Design (Almost 12 Factors)
- Java Spring Boot (Java 11 and Container Tuning)
- Spring Cloud (Microservices Framework)

Frontend:
- Nuxt.JS (Vue.js) Server Side Rendering
- Progressive Web Application
- Node.js

Database:
- MongoDB 4.2 (1 Service 1 Database in Conceptual for Microservices)
- Elasticsearch 7.2 (Recommendation & Searching)

Infrastructure
(ElasticStack: On Premise with Docker)
- Elasticsearch (Fulltext-search &Logs)
- Logstash (Migrate Data)
- Kibana (Realtime-Visualization)
- Application Performance Monitoring (APM)
- Metricbeats (Hardware & Network Monitoring)

(Microservice - Spring Cloud)
- Zuul API Gateway (Application Loadbalancer)
- Eureka Server (Service Discovery & Register)
- Config Server (Credential Management)

(Server: AWS)
- Route53 (DNS Load Balancer)
- EC2 (VM Instance)
- CentOS 7
- Docker (Container Everything Includes Elastic)

Services:
- Monstache (Golang Driver for Migrate Data from MongoDB to Elasticsearch)
- Firebase Cloud Storage
- Firebase Authenication (OAuth)
- Firebase Cloud Messaging (PWA push notication)
- Google Map

Misc:
- Postman
- Adobe XD
- Git

## Elasticstack 
![alt text](https://seniorproject.sit.kmutt.ac.th/screenshot/screenshot10.IT59-BU37.png)
