<template>
  <v-container>
  <div>
    <todo-list></todo-list>
  </div>
  </v-container>
</template>

<script>
import TodoList from './TodoList'

export default {
    name: "LayoutList",
  components: {
    TodoList
  }
}
</script>
<style lang="css">
.v-content {
  max-width: 100%;
  /* background-image: url(../assets/bg.png) !important; */
  /* background-repeat: repeat; */
  /* background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background: transparent; */
}



</style>

