<template>
  <el-main>
    <div v-for="item in messages">
      <p><span>{{item.sendTime}}</span></p>
      <div v-if="item.senderId==userId">
        <el-avatar>

        </el-avatar>
      </div>
    </div>
  </el-main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "MyMessageDetail",
    data(){
      return{
        id:'',
        userId:'',
        messages:[],
        allMessages:[],
      }
    },
    mounted() {
      this.id=this.$route.params.id
      let url =`http://localhost:8088/message/getAllMessage`
      this.userId=JSON.parse(sessionStorage.getItem('form')).id
      axios.get(url,{
        params:{
          user_id:this.userId
        }
      }).then(res=>{
        this.allMessages=res.data
        console.log(this.allMessages)
        let tmp=[]
        for(let i in this.allMessages){
          if(this.allMessages[i].senderId==this.id||this.allMessages[i].recipientId==this.id)
            tmp.push(this.allMessages[i])
        }
        console.log(tmp)
        this.messages=tmp
      })
    },
    watch:{
      $route:{
        handler(oldUrl,newUrl){
          this.id =oldUrl.params.id
          let tmp=[]
          for(let i in this.allMessages){
            if(this.allMessages[i].senderId==this.id||this.allMessages[i].recipientId==this.id)
              tmp.push(this.allMessages[i])
          }
          console.log(tmp)
          this.messages=tmp
        },
        immediate:true
      }
    }
  }
</script>

<style scoped>

</style>
