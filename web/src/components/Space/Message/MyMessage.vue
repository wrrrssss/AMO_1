<template>
  <div>
    <div v-for="(item,i) in messages" :key="i" style="background-color: #E6E6FA;height: 50px;margin: 5px;line-height: 50px">
      <div style="float: left;width: auto;height: auto;line-height: 50px">
        {{item.sendTime}}
      </div>
      <p>{{item.content}}</p>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "MyMessage",
      data(){
        return{
          messages:[],
        }
      },
      mounted() {
        let id = JSON.parse(sessionStorage.getItem('form')).id
        let url =`http://localhost:8088/message/getAllMessage`
        axios.get(url,{
          params:{
            user_id:id
          }
        }).then(res=>{
          this.messages=res.data
          console.log(this.messages)
        })
      }
    }
</script>

<style scoped>

</style>
