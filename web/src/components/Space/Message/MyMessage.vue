<template>
  <el-container>
    <el-aside width="200px">
      <el-menu>
        <el-menu-item v-for="(item,i) in allFriends" :key="i">
          <router-link :to="{path:'/space/message/mymessage/'+item,params: {id:item}}"
                       style="text-decoration: none">
            {{item}}
          </router-link>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "MyMessage",
      data(){
        return{
          allMessages:[],
          allFriends:[],
        }
      },
      created() {

        let url =`http://localhost:8088/message/getAllMessage`
        let userId=JSON.parse(sessionStorage.getItem('form')).id
        this.allFriends.push(userId)
        axios.get(url,{
          params:{
            user_id:userId
          }
        }).then(res=>{
          this.allMessages=res.data
          for (let i in this.allMessages){
            if(this.allMessages[i].senderId==this.allFriends[0]){
              if(this.allFriends.includes(this.allMessages[i].recipientId)==false)
                this.allMessages[i].recipientId
                this.allFriends.push(this.allMessages[i].recipientId)
            }
            else{
              if(this.allFriends.includes(this.allMessages[i].senderId)==false)
                this.allFriends.push(this.allMessages[i].senderId)
            }
          }
          this.allFriends.shift()
        })
      },
      methods:{
        getMessages(id){
          let tmp=[]
          for (let i in this.allMessages){
            if(this.allMessages[i].senderId==id||this.allMessages[i].recipientId==id)
              tmp.push(this.allMessages[i])
          }
          /*console.log(tmp)*/
          return tmp
        }
      }
    }
</script>

<style scoped>

</style>
