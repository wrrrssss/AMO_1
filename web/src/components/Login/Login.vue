<template>
  <div id=login_form>
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" label-position="right">
      <el-form-item label="用户名">
        <el-input id="username" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input id="pw" type="password" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">登录</el-button>
        <router-link to="/register"><el-button>注册</el-button></router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {hex_md5} from '../../assets/MD5'

  export default {
    name: 'Login',
    data() {
      return {
        form: {
          username: '',
          password: '',
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 4, max: 20, message: '长度在 4 到 20 个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
          ]
        },
      }
    },
    methods: {
      onSubmit(formName) {
        console.log(formName)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form.password=hex_md5(this.form.password)
            let url = `http://localhost:8088/user/login`
            axios.post(url,this.form).then((res => {
              console.log(res)
              if(res.data.success==true){
                let form=JSON.stringify(res.data.user)
                sessionStorage.setItem('form',form)
                let url1 = `http://localhost:8088/friend/getAllGroup`
                axios.get(url1,{
                  params:{
                    user_id:JSON.parse(sessionStorage.getItem('form')).id
                  }
                }).then(res=>{
                  this.groups=res.data
                  let group = JSON.stringify(this.groups)
                  sessionStorage.setItem('group',group)
                })
                  this.$router.push({
                    path:'/'
                  })
              }
              else
                alert("请输入正确的用户名和密码")
            }))

          } else {
            alert('请输入有效的用户名和密码！');
            return false;
          }
        });

      },
    }
  }
</script>

<style scoped>

</style>
