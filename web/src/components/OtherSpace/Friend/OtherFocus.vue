<template>
  <div>
    <div v-for="(item,i) in groupInfo" :key="i" class="focus">
      <div class="content">
        <a class="title">{{item.friendName}}</a>
      </div>
      <div class="focus-action" v-if="isFocus[i]">
        <el-dropdown>
          <span class="el-dropdown-link">
            已关注<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="noFocus(i)">取消关注</el-dropdown-item>
            <el-dropdown-item @click.native="sendMessage(i)">发消息</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="focus-action" v-else>
        <el-button @click="focus(i)">
          关注
        </el-button>
      </div>
    </div>
    <el-dialog title="选择分组" :visible.sync="dialogVisible" width="30%">
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
    name: 'OtherFocus',
    data(){
      return{
        groupId:'',
        groupInfo:'',
        groups:[],
        dialogVisible:false,

        tmpForm:{
          userId:'',
          friendId:'',
          friendName:'',
          groupsId:'',
        },

        isFocus:[],
        rules:{
          groupsId:[
            { required: true, message: '请选择分组', trigger: 'change' }
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
        for(let i in this.groupInfo){
          let url =  `http://localhost:8088/friend/isFriend`
          axios.get(url,{
            params:{
              user_id:JSON.parse(sessionStorage.getItem('form')).id,
              friend_id:this.groupInfo[i].friendId
            }
          }).then(res=>{
            console.log(res)
            this.isFocus.push(res.data)
          })
        }
      })
      this.groups=JSON.parse(sessionStorage.getItem('group'))
    },
    methods: {
      focus(index) {
        this.tmpForm.userId=JSON.parse(sessionStorage.getItem('form')).id
        this.tmpForm.friendId=this.groupInfo[index].friendId
        this.tmpForm.friendName=this.groupInfo[index].friendName
        this.dialogVisible=true
      },
      submit(){
        let url = `http://localhost:8088/friend/addFriend`
        axios.post(url,this.tmpForm).then(res=>{
          if(res.data.success==true) {
            alert("关注成功")
          }
        })
        this.$router.go(0)
      },
      noFocus(index){
        let url = `http://localhost:8088/friend/deleteFriend`
        axios.get(url,{
          params:{
            user_id:JSON.parse(sessionStorage.getItem('form')).id,
            friend_id:this.groupInfo[index].friendId,
          }
        }).then(res=>{
          if(res.data.success==true){
            alert("已删除好友")
            this.$router.go(0)
          }
        })
      },
      sendMessage(index){
        let id = this.groupInfo[index].friendId
        this.$router.push({
            path:'/space/message/chat/'+id,
            query:{
              id:id
            }
          }
        )
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
