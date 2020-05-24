<template>
  <div>
    <div v-for="(item,i) in groupInfo" :key="i" class="focus">
      <div class="content">
        <a class="title">{{item.friendName}}</a>
      </div>
      <div class="focus-action">
        <el-dropdown>
          <span class="el-dropdown-link">
            已关注<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="updateGroup(i)">设置分组</el-dropdown-item>
            <el-dropdown-item @click.native="noFocus(i)">取消关注</el-dropdown-item>
            <el-dropdown-item @click="sendMessage">发消息</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <el-dialog title="更换分组" :visible.sync="dialogVisible" width="30%">
      <el-form ref="tmpForm" :model="tmpForm" label-width="80px">
        <el-form-item label="选择分组" prop="groupsId">
          <el-select v-model="tmpForm.groupsId">
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
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Focus',
    data(){
      return{
        groupId:'',
        groupInfo:'',
        groups:[],
        dialogVisible:false,
        tmpForm:{
          id:'',
          userId:'',
          friendId:'',
          friendName:'',
          beginTime:'',
          remarks:'',
          groupsId:'',
        },
        rules:{
          groupsId:[
            { required: true, message: '请选择新的分组', trigger: 'change' }
          ]
        }
      }
    },
    created () {
      this.groupId=this.$route.params.id
      let url = `http://localhost:8088/friend/getAllFriend`
      axios.get(url,{
        params:{
          group_id:this.groupId
        }
      }).then(res=>{
        this.groupInfo=res.data
        console.log(this.groupInfo)
      })
      this.groups=JSON.parse(sessionStorage.getItem('group'))
    },
    methods: {
      updateGroup (index) {
        this.dialogVisible=true
        this.tmpForm.id=this.groupInfo[index].id
        this.tmpForm.userId=this.groupInfo[index].userId
        this.tmpForm.friendId=this.groupInfo[index].friendId
        this.tmpForm.friendName=this.groupInfo[index].friendName
        this.tmpForm.beginTime=this.groupInfo[index].beginTime
        this.tmpForm.remarks=this.groupInfo[index].remarks
        console.log(this.tmpForm)
      },
      submit(){
        let url = `http://localhost:8088/friend/modifyFriend`
        axios.post(url,this.tmpForm).then(res=>{
          if(res.data.success==true) {
            alert("修改成功")
          }
        })
        this.$router.go(0)
      },
      noFocus(index){
        let url = `http://localhost:8088/friend/deleteFriend`
        axios.get(url,{
          params:{
            user_id:this.groupInfo[index].userId,
            friend_id:this.groupInfo[index].friendId,
          }
        }).then(res=>{
          if(res.data.success==true){
            alert("已删除好友")
            this.$router.go(0)
          }
        })
      },
      sendMessage(){

      },
      },

    }
</script>

<style scoped>
  .focus{
    border-bottom: 1px solid #eee;
    display: block;
    padding: 30px 0;
    position: relative;
    line-height: 1;
  }
  .content{
    padding: 0 200px 0 0;
    position: relative;
    margin-left: 88px;
    margin-top: 10px;
    float: left;
  }
  .title{
    font-size: 16px;
    line-height: 1;
  }
  .description{
    line-height: 14px;
    font-size: 12px;
    color: #6d757a;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .focus-action{
    margin-right: 10px;
    margin-top: 10px;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
