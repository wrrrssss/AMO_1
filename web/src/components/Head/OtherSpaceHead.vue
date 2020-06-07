<template>
  <el-container>
    <el-header height="210px">
      <div class="header">
        <div class="user-log">
          <div class="user-info">
            <el-avatar :src="require('../../assets/'+userInfo.picture+'.JPG')"></el-avatar>
            <span>{{this.userInfo.username}}</span>
          </div>
          <div class="user-button">
            <el-dropdown placement="bottom" v-if="isFocus">
              <el-button type="success" >
                已关注<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="noFocus">取消关注</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-button @click="getFocus" v-else>
              关注
            </el-button>
            <el-button type="primary" @click="sendMessage(userInfo.id)">
              发消息
            </el-button>
          </div>
        </div>
      </div>
    </el-header>
    <el-main>
      <div class="user-list">
        <el-menu default-active="activeIndex" mode="horizontal" style="height: 52px" @select="handleSelect">
          <el-menu-item index="1"><router-link :to="'/space/'+this.$route.params.userId+'/dynamics'" style="font-size: 18px;text-decoration: none">动态</router-link></el-menu-item>
          <el-menu-item index="2"><router-link :to="'/space/'+this.$route.params.userId+'/collections'"style="font-size: 18px;text-decoration: none">收藏</router-link></el-menu-item>
          <el-menu-item index="3"><router-link :to="'/space/'+this.$route.params.userId+'/manuscript'"style="font-size: 18px;text-decoration: none">投稿</router-link></el-menu-item>
          <el-menu-item index="4"><router-link :to="'/space/'+this.$route.params.userId+'/friend'"style="font-size: 18px;text-decoration: none">好友</router-link></el-menu-item>
          <el-menu-item index="5"><router-link :to="'/space/'+this.$route.params.userId+'/history'"style="font-size: 18px;text-decoration: none">历史</router-link></el-menu-item>
        </el-menu>
      </div>
    </el-main>
    <el-dialog title="关注" :visible.sync="dialogVisible1" width="30%">
      <el-form ref="focus" :model="focus" :rules="rules">
        <el-form-item label="选择分组" prop="groupsId">
          <el-select v-model="focus.groupsId">
            <el-option v-for="(group,m) in groups"
                       :label="group.name"
                       :key="m"
                       :value="group.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submitGroup('focus')">确 定</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
  import axios from 'axios'
    export default {
        name: "OtherSpaceHead",
      data() {
        //这是导航栏
        return {
          activeIndex: '',
          userInfo:{},
          groups:[],
          dialogVisible1:false,
          isFocus:false,

          rules:{
            groupsId:[
              { required: true, message: '请选择关注分组', trigger: 'change' },
            ],
          },
          focus:{
            userId:'',
            friendId:'',
            friendName:'',
            beginTime:'',
            remarks:'',
            groupsId:'',
          },
        };
      },
      mounted () {
          if(this.$route.params.userId==JSON.parse(sessionStorage.getItem('form')).id){
            this.$router.push({
              path:'/space'
            })
          }
          else{
            let url = `http://localhost:8088/user/selById/${this.$route.params.userId}`
            axios.get(url).then(res=>{
              this.userInfo=res.data
              sessionStorage.setItem('other',JSON.stringify(this.userInfo))
            })
            let url1 =`http://localhost:8088/friend/isFriend`
            axios.get(url1,{
              params:{
                user_id:JSON.parse(sessionStorage.getItem('form')).id,
                friend_id:this.$route.params.userId
              }
            }).then(res=>{
              this.isFocus=res.data
            })
          }
      },

      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        },
        sendMessage(id){
          this.$router.push({
              path: '/space/message/chat/' + id,
              query: {
                id: id
              }
            }
          )
        },
        getFocus(){
          this.focus.userId=JSON.parse(sessionStorage.getItem('form')).id
          this.focus.friendId=JSON.parse(sessionStorage.getItem('other')).id,
          this.focus.friendName=JSON.parse(sessionStorage.getItem('other')).username,
          this.groups=JSON.parse(sessionStorage.getItem('group'))
          this.dialogVisible1=true
        },
        submitGroup(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let url1 = `http://localhost:8088/message/sendMessage`
              let messageForm ={
                senderId:JSON.parse(sessionStorage.getItem('form')).id,
                recipientId:JSON.parse(sessionStorage.getItem('other')).id,
                content:JSON.parse(sessionStorage.getItem('form')).username+'关注了您'
              }
              axios.post(url1,messageForm)

              let url=`http://localhost:8088/friend/addFriend`
              axios.post(url,this.focus).then(res=>{
                if(res.data.success==true){
                  alert('关注成功')
                  this.$router.go(0)
                }
                else
                  alert('关注失败')
              })
            }
            else {
              return false;
            }
          })
        },
        noFocus(){
          let url1 = `http://localhost:8088/message/sendMessage`
          let messageForm ={
            senderId:JSON.parse(sessionStorage.getItem('form')).id,
            recipientId:JSON.parse(sessionStorage.getItem('other')).id,
            content:JSON.parse(sessionStorage.getItem('form')).username+'取消关注了您'
          }
          axios.post(url1,messageForm)

          let url=`http://localhost:8088/friend/deleteFriend`
          axios.get(url,{
            params:{
              user_id:JSON.parse(sessionStorage.getItem('form')).id,
              friend_id:JSON.parse(sessionStorage.getItem('other')).id,
            }
          }).then(res=>{
            if(res.data.success==true){
              this.$router.go(0)
            }
          })
        },
      }
    }
</script>

<style scoped>
  .el-header{
    padding-left: 150px;
    padding-right: 150px;
  }
  .header{
    height: 200px;
    background: url("../../assets/timg.jpg") no-repeat;
    background-size: cover;
  }
  .user-log{
    padding-top: 130px;
    position: relative;
  }
  .user-info{
    padding:10px;
    float: left;
  }
  .user-button{
    padding: 10px;
    float: right;
  }
  .user-list{
    float: left;
    height: 100%;
  }
</style>
