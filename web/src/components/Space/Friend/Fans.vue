<template>
  <div>
    <div v-for="(item,i) in fans" :key="i">
      <p>{{item.username}}</p>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Fans',
    data(){
      return{
        fans:[],
      }
    },
    mounted(){
      let url =`http://localhost:8088/friend/`
      axios.get(url,{
        params:{
          user_id:JSON.parse(sessionStorage.getItem('form')).id
        }
      }).then(res=>{
        console.log(res)
        this.fans=res.data
      })
    },
    methods:{

    }
  }
</script>

<style scoped>

</style>
