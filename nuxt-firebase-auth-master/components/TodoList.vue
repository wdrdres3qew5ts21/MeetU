<template>
  <div class="container">
    <v-card rounded outlined class="mx-auto">
      <div class="px-3">
        <v-layout>
          <v-flex xs12>
            <!-- <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              :value="description"
              placeholder="Write somthing..."
            @input="updateValue($event.target.value, 'description')"></v-text-field>-->

            <!-- <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              :value="description"
              placeholder="Write somthing..."
            ></v-text-field>-->
            <!-- <input type="text" 
        
            placeholder="Write something..." 
            class="questrial no-top-padding"
            height="44px"
            v-model="list.description"
            @input="updateValue($event.target.value, 'description')">-->
            <!-- 
            <input type="text" 
           
            v-model="postInput"
            placeholder="Write something..." 
            class="questrial no-top-padding"
            height="44px"
            @input="updateValue($event.target.value, 'postInput')">-->
            
              <!-- <field-list
                v-for="(list, index) in lists"
                :key="index"
                @update:description="value => list.description = value"
                :description="list.description"
              ></field-list> -->
           


          </v-flex>
        </v-layout>
        
      </div>
    </v-card>
  
    {{lists}}
    <!-- <pre>{{ lists }}</pre> -->
  </div>
</template>

<script>
import FieldList from "./FieldList";

export default {
  name: "TodoList",
  components: {
    FieldList
  },

  data() {
    return {
      lists: [{ description: "" }]
    };
  },

  methods: {
    addField() {
      this.lists.push({ description: "" });
    }
  }
};
</script>

<style>
.container {
  width: 960px;
  margin: 0 auto;
  text-align: center;
}
.field-warp {
  display: block;
}
.btn-add {
  margin-top: 30px;
}
</style>

