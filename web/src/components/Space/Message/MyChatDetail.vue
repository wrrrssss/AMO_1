<template>
  <el-main>
    <el-container v-for="item in allMessages">
      <el-container v-if="item.senderId==userId" class="dialog">
        <el-header>{{item.sendTime}}</el-header>
        <el-main>
          <div>
            <el-avatar :src="require('../../../assets/'+myPicture+'.JPG')" style="float: right"></el-avatar>
            {{item.content}}
          </div>
        </el-main>
      </el-container>
      <el-container v-else class="dialog">
        <el-header>{{item.sendTime}}</el-header>
        <el-main>
          <div>
            <el-avatar :src="require('../../../assets/'+userInfo.picture+'.JPG')" style="float: left"></el-avatar>
            {{item.content}}
          </div>
        </el-main>
      </el-container>
    </el-container>
    <el-form :inline="true" :model="content">
      <el-form-item label="发送信息">
        <el-input type="textarea" :row="2" v-model="content">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="sendMessage">发送</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  import axios from "axios";

  export default {
    name: "MyChatDetail",
    data(){
      return{
        id:'',
        userId:'',
        allMessages:[],
        userInfo:{},
        myPicture:'',
        content:'',
      }
    },
    mounted() {
      this.myPicture=JSON.parse(sessionStorage.getItem('form')).picture
      this.id=this.$route.params.id
      let url1=`http://localhost:8088/user/selById/${this.id}`
      axios.get(url1).then(res=>{
        this.userInfo=res.data
      })
      let url =`http://localhost:8088/chat/getAllMessage`
      this.userId=JSON.parse(sessionStorage.getItem('form')).id
      axios.get(url,{
        params:{
          user1_id:this.userId,
          user2_id:this.id
        }
      }).then(res=>{
        console.log(res)
        this.allMessages=res.data
      })
    },
    methods:{
      sendMessage(){
        let url = `http://localhost:8088/chat/sendMessage`
        let tmp = {
          senderId:this.userId,
          recipientId:this.id,
          content:this.content
        }
        axios.post(url,tmp).then(res=>{
          if(res.data.success==true){
            this.$router.go(0)
          }
        })
      }
    },
    watch:{
      $route:{
        handler(oldUrl,newUrl){
          this.id =oldUrl.params.id
          console.log(this.id)
          this.myPicture=JSON.parse(sessionStorage.getItem('form')).picture
          let url1=`http://localhost:8088/user/selById/${this.id}`
          axios.get(url1).then(res=>{
            this.userInfo=res.data
          })
          let url =`http://localhost:8088/chat/getAllMessage`
          this.userId=JSON.parse(sessionStorage.getItem('form')).id
          axios.get(url,{
            params:{
              user1_id:this.userId,
              user2_id:this.id
            }
          }).then(res=>{
            console.log(res)
            this.allMessages=res.data
          })
        },
        immediate:true
      }
    }
  }
</script>

<style scoped>
.dialog{
  margin: 5px;
  background-color: #E6E6FA;
  border-radius:5px;
}
</style>
