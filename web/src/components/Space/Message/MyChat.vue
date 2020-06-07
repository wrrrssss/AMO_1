<template>
  <el-container>
    <el-aside width="200px">
      <el-menu>
        <el-menu-item v-for="(item,i) in allFriendsName" :key="i">
          <router-link :to="{path:'/space/message/chat/'+allFriends[i],params: {id:allFriends[i]}}"
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
      name: "MyChat",
      data(){
        return{
          allMessages:[],
          allFriends:[],
          allFriendsName:[],
        }
      },
      mounted() {
        let userId=JSON.parse(sessionStorage.getItem('form')).id
        let url1 = `http://localhost:8088/chat/getAllMessageOfUser`
        axios.get(url1,{
          params:{
            user_id:userId
          }
        }).then(res=>{
          console.log(res)
          let tmp = res.data
          if(this.$route.query.id!=null)
            tmp.unshift({recipientId:this.$route.query.id,senderId:userId})
          console.log('朋友')
          console.log(tmp)
          for (let i in tmp){
            if(tmp[i].senderId==userId){
              if(this.allFriends.indexOf(tmp[i].recipientId)===-1){
                this.allFriends.push(tmp[i].recipientId)
              }
            }
            else {
              if(this.allFriends.indexOf(tmp[i].senderId)===-1){
                this.allFriends.push(tmp[i].senderId)
              }
            }
          }
          for (let i in this.allFriends){
            let url = `http://localhost:8088/user/selById/${this.allFriends[i]}`
            axios.get(url).then(res=>{
              this.allFriendsName.push(res.data.username)
              console.log('名字')
              console.log(this.allFriendsName)
            })
          }
        })
      },
      methods:{

      }
    }
</script>

<style scoped>

</style>
