<template>
  <div>
    <el-container>
      <el-aside style="width: 15%;height: 100%">
        <el-menu> <!--收藏夹目录-->
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-circle-plus" @click="addCollection"></i>我的收藏</template>
            <el-menu-item-group>
              <el-menu-item v-for="(item,i) in collections" :key="i">
                <router-link :to="'/space/collections/detail/'+item.id">{{item.name}}</router-link>
                <el-dropdown placement="bottom-start">
                <span><i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="editColleciton(i)">编辑分组</el-dropdown-item>
                  <el-dropdown-item @click.native="deleteCollection(i)">删除分组</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-dialog title="编辑收藏夹" :visible.sync="dialogVisible1" width="30%">
      <el-form :model="tmpForm" :rules="rules" ref="tmpForm">
        <el-form-item label="新的收藏夹名" prop="name">
          <el-input v-model="tmpForm.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submit1('tmpForm')">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="新建收藏夹" :visible.sync="dialogVisible2" width="30%">
      <el-form  :model="createForm" :rules="rules" ref="createForm">
        <el-form-item label="新的收藏夹名" prop="name">
          <el-input v-model="createForm.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="submit2('createForm')">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import axios from 'axios'
export default {
  name: `Collection`,
  data(){
    return{
      userInfo:{},
      collections:[],
      dialogVisible1:false,
      dialogVisible2:false,
      createForm:{
        user_id:'',
        create_time:'',
        name:'',
      },
      tmpForm:{
        id:'',
        user_id:'',
        create_time:'',
        name:'',
        tag:'',
      },
      rules:{
        name:[
          { required: true, message: '请输入名字', trigger: 'blur' }
        ]
      },
    }
  },
  mounted () {
    this.userInfo=JSON.parse(sessionStorage.getItem('form'))
    let url =`http://localhost:8088/collect/getAllList`
    axios.get(url,{
      params:{
        user_id:this.userInfo.id
      }
    }).then(res=>{
      console.log(res)
      this.collections=res.data
    })
  },
  methods:{
    addCollection(){
      this.createForm.user_id=this.userInfo.id
      this.createForm.create_time=new Date()
      this.dialogVisible2=true
    },
    editColleciton(index){
      this.tmpForm.id=this.collections[index].id
      this.tmpForm.user_id=this.collections[index].user_id
      this.tmpForm.create_time=this.collections[index].create_time
      this.tmpForm.tag=this.collections[index].tag
      this.dialogVisible1=true
    },
    deleteCollection(index){
      let url=`http://localhost:8088/collect/deleteFavoritesList`
      axios.get(url,{
        params:{
          id:this.collections[index].id
        }
      }).then(res=>{
        if(res.data.success==true){
          alert("删除成功")
          this.$router.go(0)
        }
        else
          alert("删除失败")
      })
    },
    submit1(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url=`http://localhost:8088/collect/modifyFavoritesList`
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
          let url=`http://localhost:8088/collect/createFavoritesList`
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
  }
}
</script>

<style scoped>
.el-container{
  padding: 0;
  margin: 10px 0 0 0;
}
.image {
  height: 67%;
  width: 100%;
}
  .image img{
    height: auto;
    width: auto;
    max-height: 100%;
    max-width: 100%;
  }
</style>
