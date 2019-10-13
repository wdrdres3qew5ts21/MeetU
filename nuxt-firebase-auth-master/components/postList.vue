<template>
  <div>
      <v-card rounded outlined class="mx-auto">
          <v-layout>
          <v-flex xs12>
              <input type="text" :value="description"
               placeholder="Write something..." 
              class="questrial no-top-padding"
              height="44px"
              @input="updateValue($event.target.value, 'description')">
            <!-- <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              :value="description" 
              placeholder="Write somthing..."
              @input="updateValue($event.target.value, 'description')"
            ></v-text-field> -->
          </v-flex>
        </v-layout>
	  
         <!-- <v-text-field
              class="questrial no-top-padding"
              height="44px"
              rounded
              placeholder="Write somthing..."
                :value="description"
             @input="updateValue($event.target.value, 'description')"
            ></v-text-field> -->
      </v-card>
  </div>
</template>

<script>
export default {
  name:'postList',
  props: [ 'description'],
  
  methods: {
    updateValue(value, type) {
    	
    	this.$emit('update:description', value)
    }
  }

}
</script>
