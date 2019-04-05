<template>
  <v-layout row justify-start align-start >
        <!-- <div class="wrapper">
            <div class="container">
              <div class="ribbon-4 ribbon">
              
              </div>
            </div>
        </div> -->
      
        <!-- <v-subheader>Test</v-subheader> -->
        <!-- <v-container fluid grid-list-sm>
          <v-layout row wrap>
            <v-flex v-for="i in 6" :key="i" xs4>
              <img :src="`https://randomuser.me/api/portraits/men/${i + 20}.jpg`" class="image" alt="lorem" width="100%" height="100%">
            </v-flex>
          </v-layout>
        </v-container> -->
        <div class="flex-container">
        <nuxt-link class="linkEvent" v-for="event in popularEventList" :key="event.eventId" 
                :to="{path:'/event',query:{eventId:event.eventId}}">       

          <v-flex xs12 >
              <h1 style="color: black"> {{$store.state.user}} </h1>
              
                  <div class="mt-5">
                    <div class="flex-item">
                      <img v-bind:src="event.eventPhoto" alt="" height="100px">
                        <p class="eventName">{{event.eventName}}</p>
                        <p class="eventDate">{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
                    </div>
                  </div>
          </v-flex>
        </nuxt-link>
        </div>
        <!-- <div class="flex-container">
        <nuxt-link v-for="event in recentlyAdded" :key="event.eventId" 
              :to="{path:'/event',query:{eventId:event.eventId}}">       


          <v-flex xs12 >
              <h1 style="color: black"> {{$store.state.user}} </h1>
              <div class="flex-item">
                  <div class="mt-5">
                      <img v-bind:src="event.eventPhoto" alt="" height="100px">
                      <p>{{event.eventName}}</p>
                      <p>{{event.eventDate.getDate()}}/{{event.eventDate.getMonth()}}/{{event.eventDate.getYear()}}</p>
                    
                  </div>
              </div>

          </v-flex>
        </nuxt-link> -->
        <!-- </div> -->


        

        
        
      
  </v-layout>
  
  
  

</template>

<script>

export default {
  components: {

    
  },
  data(){
    return {
      popularEventList:[
        {
          eventId: '11111a',
          eventName: 'Icecream Social',
          eventPhoto: 'https://news.cci.fsu.edu/files/2014/03/2014-CCI-SLC-Ice-Cream-Social-Flyer.png',
          eventDate: new Date(1553644800),
        },
        {
          eventId: '11112a',
          eventName: 'Charity Auction',
          eventPhoto: 'http://jenniferhowedesigns.com/wp-content/uploads/2018/06/goodcharityposter.png',
          eventDate: new Date(1553644800),
        },
        {
          eventId: '11113a',
          eventName: 'Original Festival',
          eventPhoto: 'https://i.pinimg.com/originals/0f/04/6f/0f046f654320d387d7608a754f205d1e.png',
          eventDate: new Date(1553644800),
        },
        {
          eventId: '11114a',
          eventName: 'Mars Event',
          eventPhoto: 'https://s3.amazonaws.com/thumbnails.venngage.com/template/70fdebc4-7bb7-49fc-9caf-700f890de92b.png',
          eventDate: new Date(1553644800),
        },
        {
          eventId: '11115a',
          eventName: 'Night Market',
          eventPhoto: 'https://stumbler.co.za/wp-content/uploads/2017/11/26685184_140352653308218_9209494944451264947_o-4.jpg',
          eventDate: new Date(1553644800),
        }
      ],
      recentlyAdded:[{
        eventId: '11111b',
        eventName: 'Ballon Festival',
        eventPhoto:'https://marketplace.canva.com/MADOPmFuuDk/1/0/thumbnail_large/canva-blue-red-balloon-festival-event-poster-MADOPmFuuDk.jpg',
        eventDate: new Date(1553731200),
      },
      {
        eventId: '11112b',
        eventName: 'Annual Sports Fest',
        eventPhoto:'https://marketplace.canva.com/MADOPq1rLE0/1/0/thumbnail_large/canva-black-simple-sports-event-poster-MADOPq1rLE0.jpg',
        eventDate: new Date(1553731200),

      },
      {
        eventId: '11113b',
        eventName: 'Will Draw For Food',
        eventPhoto:'https://marketplace.canva.com/MADOPkH9JIQ/1/0/thumbnail_large/canva-sketch-food-drawing-event-poster-MADOPkH9JIQ.jpg',
        eventDate: new Date(1553731200),
      },
      {
        eventId: '11114b',
        eventName: 'SPLASH Art Festival',
        eventPhoto:'https://marketplace.canva.com/MADOPrc2V5c/1/0/thumbnail_large/canva-blue-green-festival-event-poster-MADOPrc2V5c.jpg',
        eventDate: new Date(1553731200),
      },
      {
        eventId: '11115b',
        eventName: 'Cady At Home for The Holiday',
        eventPhoto:'https://marketplace.canva.com/MADI6arDCak/1/0/thumbnail_large/canva-blue-and-red-christmas-event-letter-sized-poster-MADI6arDCak.jpg',
        eventDate: new Date(1553731200),
      }

      ]
    }
  }
}
</script>

<style scoped>
/* .fake {
  background: blue;
}
.wrapper {
  margin-top: -7.5em;
}
.flexWrapper {
  display: flex;
  justify-content: center;
  flex-flow: row wrap;
 
}
/* .container {
  height: calc(100vh - 100px);
  align-items: center;
  align-content: center;
  display: flex;
  justify-content: center;
} */


.flex-container {
  display: flex;
  flex-wrap: wrap;
}
.flex-container::after {
  content: '';
  width: 100%;
}
.flex-item:last-child { /* or `:nth-child(n + 4)` */
  order: 1;
}

.linkEvent{
  text-decoration-line: none;
}



.eventName{
  color: #000;
  
}
.eventName:hover{
  color: #FC5577
}

.eventDate{
  color: #000;
}

.wrapper{
  position: absolute;
  /* top: 50%;
  left: 50%; */
  /* transform: translate(-30%,-30%); */
}

.container{
  margin-top: 200px;
  width: 350px;
  height: 420px;
  
 

  /* box-shadow: 0 0 10px rgba(150, 150,150,0.5); */
  position: relative;

}

.ribbon{
  position: absolute;
  
}

.ribbon-4{
  width: 550px;
  height: 50px;
  background: midnightblue;
  top: -40%;
  left:50px;
  
  
}
.ribbon-4:before{
  content:"";
  position: absolute;
  top: -10px;
  right: 0px;
  border-left: 5px solid #14455b;
  border-right: 5px solid transparent;
  border-top: 5px solid transparent;
  border-bottom: 5px solid #14455b;
  
}
.ribbon-4:after{
  content: "";
  position: absolute;
  top: 0px;
  left: -1px;
  border-left: 25px solid #fff;
  border-right: 25px solid transparent;
  border-top: 25px solid transparent;
  border-bottom: 25px solid transparent;
  
}


</style>
