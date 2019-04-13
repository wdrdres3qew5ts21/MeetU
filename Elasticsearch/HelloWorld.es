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




