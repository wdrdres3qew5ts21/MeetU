Cloud Native Microservices: Event Management Platform (Elasticsearch) MeetU: Platform กิจกรรมที่เข้ามามีส่วนช่วยเหล่าวัยรุ่นไฟแรงที่มีพลังสร้างสรรค์เป็นช่วงเวลาแห่งการทำกิจกรรมได้มาสร้างกิจกรรมและทำกิจกรรมดีๆร่วมกัน โดย MeetU มีจุดเด่นในเรื่องของการค้นหากิจกรรมตามที่พวกคุณชื่นชอบได้ด้วยระบบแนะนำกิจกรรม หรือจะเป็นการไปค้นพบกิจกรรมแนวใหม่ยอดนิยมที่ทำให้คุณได้หลุดออกมาจาก comfort zone เรียนรู้กิจกรรมใหม่ๆก็ได้ และสิ่งที่มาช่วยเป็นตัวขับเคลื่อนให้กิจกรรมสนุกขึ้นนั้นก็คือระบบ Leader Board ที่สามารถให้คุณแสดงคะแนนกิจกรรมจากกิจกรรมที่คุณเคยไปเข้าร่วมได้ พร้อมกับพบปะผู้คนที่มีความสนใจเหมือนกัน ! สำหรับในด้าน Technology เอง MeetU มาพร้อมกับสถาปัตยกรรม Application แบบ Microservices ซึ่งมามีส่วนช่วยในการออกแบบอย่าง Module ในอนาคตนั้นสามารถทำได้ง่ายได้ ซึ่งสำหรับ Core Feature ที่เราเลือก Technology มาใช้นั่นก็คือ Elasticsearch Stack ที่ทำหน้าที่ทั้งในส่วนร่วมกับ Backend คือระบบการค้นหาแบบ Full Text Search ช่วยให้เราค้นหากิจกรรมต่างๆได้ง่ายขึ้น และการ Monitoring Infeastructure ของระบบ Microservices และสำหรับภาษาที่เลิอกใช้ก็คือ Java Spring boot ที่เป็น Framework การพัฒนา Backend ซึ่งมาควบคู่กับหลัก 12 Factors อันนำไปสู่การเป็น Cloud Native Application ให้สามารถ Scaling ได้ในรูปแบบ Horizontal รวมไปถึงการรองรับ Fault Torelant ได้ในระดับนึงเพราะ Application เราถูกแบ่งออกเป็น Services หลักๆ ต่อให้มี Services ใดเกิดการล่มลงไป Application ก็ยังคงมีบาง Service ที่รองรับและทำงานได้ต่อไป


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
