<template>
  <div>
    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="activeIndex">
          <el-submenu index="1">
          <template slot="title"><i class="el-icon-circle-plus" @click="addGroup"></i>我的关注</template>
            <el-menu-item-group>
            <el-menu-item v-for="(item,i) in groups" :key="i">
              <router-link :to="'/space/friend/focus/'+item.id" style="text-decoration: none" @click.native="flushCom">
                {{item.name}}
              </router-link>
              <el-dropdown placement="bottom-start">
                <span><i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="editGroup(i)">编辑分组</el-dropdown-item>
                  <el-dropdown-item @click.native="deleteGroup(i)">删除分组</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
        <el-menu>
          <el-submenu index="1">
          <template slot="title">我的粉丝</template>
            <el-menu-item index="1-1">
              <router-link to="/space/friend/fans" style="text-decoration: none"><template slot="title"><i class="el-icon-s-custom"></i>我的粉丝</template></router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-dialog title="编辑好友分组" :visible.sync="dialogVisible" width="30%">
      <el-form :model="tmpForm" :rules="rules" ref="tmpForm">
        <el-form-item label="新的分组名" prop="name">
          <el-input v-model="tmpForm.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit('tmpForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="新建好友分组" :visible.sync="dialogVisible2" width="30%">
      <el-form :model="tmpForm1" :rules="rules" ref="tmpForm1">
        <el-form-item label="新的分组名" prop="name">
          <el-input v-model="tmpForm1.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="submit2('tmpForm1')">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Friend',
    data(){
      return{
        groups:[],
        activeIndex:'1',
        dialogVisible:false,
        dialogVisible2:false,
        tmpForm:{},
        tmpForm1:{
          name:'',
          userId:'',
        },
        rules:{
          name:[
            { required: true, message: '请输入名字', trigger: 'blur' }
          ]
        },
      }
    },
    mounted () {

      this.groups=JSON.parse(sessionStorage.getItem('group'))
    },
    methods:{
      addGroup(){
        this.tmpForm1.userId=JSON.parse(sessionStorage.getItem('form')).id
        this.dialogVisible2=true
      },
      flushCom(){
        this.$router.go(0)
      },
      editGroup(index){
        this.tmpForm=this.groups[index]
        console.log(this.tmpForm)
        this.dialogVisible=true
      },
      deleteGroup(index){
        let url=`http://localhost:8088/friend/deleteGroup`
        axios.get(url,{
          params:{
            group_id:this.groups[index].id
          }
        }).then(res=>{
          console.log(res)
          if(res.data.success==true){
            alert(res.data.message)
            this.$router.go(0)
          }
            else
              alert(res.data.message)
        })
      },
      submit(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let url=`http://localhost:8088/friend/modifyGroup`
            axios.post(url,this.tmpForm).then(res=>{
              if(res.data.success==true){
                alert("修改成功")
                this.$router.go(0)
              }
              else
                alert("修改失败")
            })
          }
          else {
            return false;
          }
        })
    },
      submit2(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let url=`http://localhost:8088/friend/addGroup`
            axios.post(url,this.tmpForm1).then(res=>{
              if(res.data.success==true){
                alert("创建成功")
                this.$router.go(0)
              }
              else
                alert("创建失败")
            })
          }
          else {
            return false;
          }
        })
      }
  }
  }
</script>

<style scoped>
  .el-container{
    padding: 0;
    margin: 10px 0 0 0;
  }
</style>
