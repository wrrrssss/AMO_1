<template>
  <el-container>
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
            <a :href="'/#/space/'+userId">
              <el-avatar :src="require('../../../assets/'+avatar+'.JPG')"></el-avatar>
            </a>
          </div>
          <div class="dynamic-user-log-userInfo">
            <div style="font-size: 17px;color: #409EFF;margin: 2px 0 0 0"><span>{{username}}</span></div>
            <div style="font-size: 12px;margin: 4px 0 0 0"><span>{{item.forwardDate}}</span></div>
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
  export default {
    name: 'OtherDynamics',
    data(){
      return{
        userId:'',
        username:'',
        avatar:'',
        dynamics:[],
        manuInfo:[],
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
      this.username=JSON.parse(sessionStorage.getItem('other')).username
      this.avatar =JSON.parse(sessionStorage.getItem('other')).picture
      /*获取全部动态*/
      this.userId= JSON.parse(sessionStorage.getItem('other')).id
      let url = `http://localhost:8088/forwardData/selAll/${this.userId}`
      axios.post(url).then(res=> {
        this.dynamics = res.data
        console.log(this.dynamics)
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
      console.log(this.manuInfo)
    },
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
