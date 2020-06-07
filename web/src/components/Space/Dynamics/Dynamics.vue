<template>
  <el-container>
    <el-header height="140px" style="text-align: center">
      <el-form label-width="200px" ref="content" :model="content" :rules="rules">
        <el-form-item label="编辑动态" prop="content">
          <el-input type="textarea" :rows="3" v-model="content.content"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="createDynamic('content')">发布</el-button>
        </el-form-item>
      </el-form>
    </el-header>
  <el-main>
    <div v-for="(item,i) in this.dynamics" :key="i"
         style="background-color: darkgray;
                margin: 5px 100px 5px 100px;
                padding: 0 0 20px 0;
                min-width: 500px;
                border-radius: 5px"
    >
      <div class="dynamic-user-log">
        <div class="dynamic-user-log-avatar">
          <a :href="'/#/space/'+20">
          <el-avatar :src="require('../../../assets/'+avatar+'.JPG')"></el-avatar>
          </a>
        </div>
        <div class="dynamic-user-log-userInfo">
          <div style="font-size: 17px;color: #409EFF;margin: 2px 0 0 0"><span>{{username}}</span></div>
          <div style="font-size: 12px;margin: 4px 0 0 0"><span>{{item.forwardDate}}</span></div>
        </div>
        <div class="dynamic-user-log-drop">
          <el-dropdown placement="bottom-start">
            <span><i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="deleteDynamic(item)">删除</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        </div>
      </div>
      <div class="content">
        <p style="text-indent: 50px">{{item.content}}</p>
        <div v-if="manuInfo[i]!=null" style="margin: 0 50px 0 50px;background-color: white;border-radius: 5px">
          <div class="dynamic-user-log">
            <div class="dynamic-user-log-avatar">
              <a :href="'/#/space/'+manuInfo[i].userId">
                <el-avatar :src="require('../../../assets/'+manuInfo[i].user_avatar+'.JPG')"></el-avatar>
              </a>
            </div>
            <div class="dynamic-user-log-userInfo">
              <div style="font-size: 17px;margin: 2px 0 0 0">
                <el-link :href="'/#/space/'+manuInfo[i].userId" :underline="false"><span style="color: #409EFF;">{{manuInfo[i].username}}</span></el-link>
                <span>的投稿</span>
              </div>
            </div>
          </div>
          <div>
            <img :src="require('../../../assets/'+manuInfo[i].cover+'.JPG')" style="height: 200px;width: 300px">
            <p>{{manuInfo[i].title}}</p>
          </div>
        </div>
      </div>
    </div>
  </el-main>
  </el-container>
</template>

<script>
  import axios from 'axios'
  import {makeDate} from "../../../assets/dateformat";
  export default {
    name: 'Dynamics',
    data(){
      return{
        dynamics:[],
        manuInfo:[],
        username:'',
        avatar:'',
        content:{
          content:'',
        },
        rules:{
          content:[
            { required: true, message: '请输入内容', trigger: 'blur' }
          ]
        },
      }
    },
    created() {
      /*获取头像地址*/
      this.username=JSON.parse(sessionStorage.getItem('form')).username
      this.avatar =JSON.parse(sessionStorage.getItem('form')).picture
      /*获取全部动态*/
      let id = JSON.parse(sessionStorage.getItem('form')).id
      let url = `http://localhost:8088/forwardData/selAll/${id}`
      axios.post(url).then(res=> {
        this.dynamics = res.data
        for(let i in this.dynamics){
          if(this.dynamics[i].manuscriptId!=null){
            let url=`http://localhost:8088/manuscript/selById/${this.dynamics[i].manuscriptId}`
            axios.get(url).then(res=>{
              this.$set(this.manuInfo,i,res.data)
              let url = `http://localhost:8088/user/selById/${res.data.userId}`
              axios.get(url).then(res=>{
                this.$set(this.manuInfo[i],'user_avatar',res.data.picture)
              })
            })
          }
          else {
            this.$set(this.manuInfo,i,null)
          }
        }
      })
    },
    methods:{
      deleteDynamic(item){
        let url =`http://localhost:8088/forwardData/delete/${item.id}`
        axios.post(url).then(res=>{
          if(res.data.success){
            if(item.manuscriptId!=null){
              let url = `http://localhost:8088/manuscript/cancelForward`
              axios.post(url,{
                manuscriptId:item.manuscriptId
              }).then(res=>{
                if(res.data.success==true)
                  this.$router.go(0)
              })
            }
            else
              this.$router.go(0)
          }
        })
      },
      createDynamic(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let url = `http://localhost:8088/forwardData/dynamic`
            let tmp = {
              userId:JSON.parse(sessionStorage.getItem('form')).id,
              content:this.content.content,
              forwardDate:makeDate(new Date()),
            }
            axios.post(url,tmp).then(res=>{
              if(res.data.success==true){
                alert("发布成功")
                this.$router.go(0)
              }
            })
          } else {
            return false
          }
        });
      },
    }
  }
</script>

<style scoped>
  .el-header{
    margin-left: 200px;
    text-align: center;
    line-height: 1.4;
    max-width: 600px;
  }
  .dynamic-user-log{
    height: 50px;
  }
  .dynamic-user-log-avatar{
    float: left;
    height: 50px;
    width: 50px;
    margin:5px;
  }
  .dynamic-user-log-userInfo{
    float: left;
    text-align: left;
    height: 50px;
  }
  .dynamic-user-log-drop{
    float: right;
    margin: 5px;
  }
.content{
  margin: 10px 0 10px 0;
}
</style>
