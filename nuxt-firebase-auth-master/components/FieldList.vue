<template>
    
  <div>
	  <!-- <input type="text" :value="description" placeholder="Write something ....." @input="updateValue($event.target.value, 'description')"> -->
   
   <!-- <v-input type="text" :value="description" placeholder="Write something ....."
    @input="updateValue($event.target.value, 'description')"> </v-input> -->
   <!-- <v-text-field :value="description" placeholder="Write something ....." @input="updateValue($event.target.value, 'description')"></v-text-field> -->
      <br />
  </div>
</template>

<script>
export default {
  name: "FieldList",
  props: [ 'postInput'],
  
  methods: {
    updateValue(value, type) {
  
    	this.$emit('update:postInput', value)
    }
  }

}
</script>
