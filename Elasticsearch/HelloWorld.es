GET /product

Elasticsearch ปัจจุบันตั้งแต่เวอร์ชั่น 6.0 เริ่มมีการยกเลิก Mapping Type แบบ Multiple แล้ว
เพราะว่าส่งผลกระทบต่อการทำงานของระบบนั่นเองโดยมีการยุบให้เหลือเพียงแค่ _doc พอแล้ว
ซึ่งตั้งแต่เวอร์ชั่น 7 ใครที่ตั้งชื่อแบบอื่นก็จะ Mapping Type แบบอื่นก็จะถือว่า Deprecated แล้วซึ่ง
ถ้าหากอัพเกรดไปถึง Version 8 จะมีการยกเลิก Mapping Type ตัวเก่าไปเลยจริงๆนั่นเอง
syntax /{indices}/_doc/_mapping

PUT /product/default/_mapping
{
  "properties":{
    "name":{
      "type": "text",
      "fields": {
        "keyword":{
          "type": "keyword"
        }
      }
    },
    "tags":{
      "type": "text",
      "fields": {
        "keyword":{
          "type": "keyword"
        }
      }
    }
  }
}

properties คือ Object Key ของ bodies ที่ต้องใช้เป้นตัวแรกเสมอเวลาที่มีการสร้าง Indices ของสิ่งต่างๆ
ซึ่งข้างในก็จะเป็น Object อันเป็น Attribute ของ Indices นั้นๆว่าต้องการให้มีคุณสมบัติใดบ้างนั้นเองซึ่งหลังจากนั้น
ภายใน Object Key ตัวนั้นก็จะทำการระบุคุณลักษณะเพิ่มเติมว่าเกี่ยวกับอะไรเช่นประเภทของ Attribute fields 
นั้นๆว่าต้องการให้มีคุณสมบัติใดบ้างนั้นเอง เช่นใช้เก็บตัวหนังสือสำหรับทำ Full Text Search 
ก็จะต้องระบุ data types เป็น "type": "text" (มีอีกหลายประเภทเช่นภูมิศาสตร์ ตัวเลขต่างๆ)
แต่ถ้าหากเราต้องการคุณสมบัติเพิ่มตเมินอกจากการค้นหาคำแบบแค่ Full Text Search แต่เราต้องการค้นหาแบบแบ่งหมวดหมู่
หรือที่เค้าเรียกกันว่า Aggregates Query เช่นกรองตาม Catergory -> เครื่องสำอาง ก็จะต้องใช้ ส่งที่เพิ่มมา
เรียกว่า mapping parameters ได้แก่ fields, norm, format, dynamic, properties อื่นๆอีกมากมายแต่สิ่งที่จะใช้
คือ mapping parameters ที่ชื่อว่า fields โดยเราจะสร้าง 
"fields":{
    "ชื่อเรียกตามใจเรา": {
        "type": "keyword"
    }
}
อันมีความหมายว่าเป้นการค้นหาจากประเภทที่ต้องมีความเหมือนตาม keyword นั้นเปะๆโดยการเรียกก็คือตาม mapping type ที่เราตั้งมา
เช่น tags.ชื่อเรียกตามใจเรา นั่นเองก็จะนำคำใน keyword  นั้นมาทำ aggregate แบ่งหมวดหมู่ได้นั่นเอง
refs: www.elastic.co/guide/en/elasticsearch/reference/current/mapping-params.html

PUT /product/default/_mapping
{
  "properties": {
    "created": {
      "type": "date",
      "format": "strict_date_optional_time || epoch_millis"
    }
  }
}

GET /product/default/_mapping

สำหรับในกรณีที่มีเรามี index แบบที่ปิด dynamic fields mapping ไปแล้วหมายความว่าก่อนอื่นเวลาส่งค่าใดๆก็ตามเราต้องสร้าง
ตัว mapping type ขึ้นมารับไว้ก่อนล่วงหน้าเสมอไม่อย่างนั้น elasticsearchมันก็ไม่รู้ว่าจะต้องเอาค่าไป map กับอะไรนั่นเองตัวอย่างเช่น
product: integer
description: text 
นี่คือตัวอย่างที่ "เราสร้าง mapping type ทิ้งเอาไว้ก่อน" เหมือนตัวอย่างบนๆและจากนั้นเราก็ import ข้อมูล json แบบ product, description 
เข้าไปก็จะสามารถนำค่าไป mapping ได้ตามปกติเลยนั่นเอง  แต่ถ้าหาก "อยู่ๆเราก็อยากเพิ่ม field ข้อมูลใหม่ขึ้นมาคือ discount"
แต่ว่าเราไม่สร้างตัว  mapping type ก่อนแต่เราส่งค่าไปเลยคือ
{
    "properties":{
        "product": "Dell XPS 13",
        "description": "Elegant and Aerospace Engineering laptop design",
        "discount": 4990
    }
}
ถ้าหากเป้น dynamic field mapping type ตัว field disocunt ก็จะถูก mapping กับ long ซึ่งเป็น default type นั่นเองก็จะนำคำใน
แต่ในกรณีนี้เราไม่มีโครงตัว mapping ใดๆเลยเพราะเราไม่สร้างทิ้งไว้ก่อนและยังปิด dynamic mapping fields อีกด้วยสิ่งที่เกิดขึ้นก้คือ
ระบบ Elasticsearch จะนำ field ที่ไม่มีการ mapping เอาไว้ก่อนไปเก้บดองเอาไว้ใน _source ของ meta fields ซึ่งเก็บ raw data
ของข้อมูลนั่นเองแต่ถ้าหากเราทำการค้นหา fields disocunt แล้วล่ะก็เราก็จะค้นหาไม่เจอนั่นเอง !!!
เพราะว่า : "ข้อมูล fields นั้นไม่มีการ mapping ไว้ทำให้ไม่สามารถทำ Index ในการค้นหาได้ จึงทำได้แค่นำข้อมูลไปพักเก้บไว้ก่อน"
และถ้าหากเรามาสร้าง mapping field type อีกทีทีหลังยังค้นหาไม่ได้อยุ่ดีเพราะว่าข้อมูลที่โดนแอดไปก่อนหน้าถูกแยกคนล่ะส่วนกับตัว mapping
"สิ่งที่ทำได้คือเราต้องทำการบังคับ Update Index ข้อมูลใหม่ทั้งหมดของ /product ตัวนี้ !!!"
เมื่อเราทำการ Update แล้วให้เราลองนึกถึงเหตุการณืหรือสถานการณืจริงที่ Cluster เราอยู่บน Production มีคนกำลังใช้งานอยู๋เวลาอัพเดท
แน่นอนว่าจะต้องไปแย่งกับ Transaction ของคนอื่นเขาทำให้เกิด Conflict กันและ Version ที่อัพเดทไม่ตรงกันนัน่เอง
เพราะว่าเวลาทำคำสั่ง POST /product/_update_by_query มันจะทำการสร้าง Snapshot ทิ้งเอาไว้ก่อนและคนอื่นก็ไปใช้ของใน cluster 
ตามปกติแต่พออัพเดทเสร็จข้อมูลมันไม่ตรงกันเพราะเขียน Transaction คนล่ะที่เลยต้องทำการเรียงข้อมูลใหม่แต่ข้อมูลที่เรียงคือระดับ
Meta fields ประเภท Mapping จึงไมไ่ด้ส่งผล Anomaly ต่อข้อมูลอยู่แล้ว (มันแค่เปลี่ยน Type Mapping ไม่ได้เปลีย่นเนื้อข้อมูล) 
จึงใช้คำสั่งเพิ่ม Query String  เข้าไปต่อนั่นก็คือ ?conflicts=proceed ต่อให้เกิด conflict ก็บังคับอัพเดทไปนัน่เอง !!!
POST /product/_update_by_query?conflicts=proceed

