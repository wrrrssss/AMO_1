<template>
  <el-container>
    <el-header height="140px">
      <el-form label-width="200px">
        <el-form-item label="编辑动态">
          <el-input type="textarea" :rows="3" v-model="content"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="createDynamic">发布</el-button>
        </el-form-item>
      </el-form>
    </el-header>
  <el-main>
    <div v-for="item in this.dynamics" style="background-color: darkgray">
      <p>
        <el-avatar :src="require('../../../assets/'+avatar+'.JPG')">
        </el-avatar>
        <span>{{item.forwardDate}}</span>
        <el-dropdown placement="bottom-start">
          <span><i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="deleteDynamic(item.id)">删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </p>
      <p>{{item.content}}</p>
      <div v-if="getManuscript(item.manuscriptId)">
        <p>
          <el-avatar :src="require('../../../assets/'+userInfo.picture+'.JPG')" icon="el-icon-user-solid">
          </el-avatar>
          <span>{{userInfo.username}}</span>
        </p>
        <el-image :src="require('../../../assets/'+manuInfo.cover+'.JPG')"></el-image>
      </div>
      <div v-else="getManuscript(item.manuscript_id)">
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
        avatar:'',
        manuInfo:{},
        userInfo:{},
        content:'',
      }
    },
    mounted() {
      /*获取头像地址*/
      this.avatar =JSON.parse(sessionStorage.getItem('form')).picture
      /*获取全部动态*/
      let id = JSON.parse(sessionStorage.getItem('form')).id
      let url = `http://localhost:8088/forwardData/selAll/${id}`
      axios.post(url).then(res=>{
        this.dynamics=res.data
        console.log(this.dynamics)
      })
    },
    methods:{
      getManuscript:function(id){
        if(id!=null){
          let url = `http://localhost:8088/manuscript/selById/${id}`
          axios.post(url).then(res=>{
            this.manuInfo=res.data
            let url1 =`http://localhost:8088/user/selById/${this.manuInfo.userId}`
            axios.post(url1).then(res=>{
              this.userInfo=res.data
            })
            return true
          })
        }
        else{
          return false
        }

      },
      deleteDynamic(id){
        let url =`http://localhost:8088/forwardData/delete/${id}`
        axios.post(url).then(res=>{
          if(res.data.success){
            alert('删除成功')
            this.$router.go(0)
          }
        })
      },
      createDynamic(){
        let url = `http://localhost:8088/forwardData/dynamic`
        let tmp = {
          userId:JSON.parse(sessionStorage.getItem('form')).id,
          content:this.content,
          forwardDate:makeDate(new Date()),
        }
        axios.post(url,tmp).then(res=>{
          if(res.data.success==true){
            alert("发布成功")
            this.$router.go(0)
          }
        })
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
</style>
