<template>
  <div>
    <el-input class="text-input" v-model="user.username" placeholder="用户名"></el-input>
    <el-input class="text-input" v-model="user.password" placeholder="密码"></el-input>
    <el-button type="primary" @click="register()">注册</el-button>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data () {
      return {
        user: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      register() {
        axios.post('http://localhost:8088/demo/register',this.user).then(response => {
          //获取注册操作结果
          var result = response.data;
          if (result.success){
            this.$message({
              message: result.message,
              type: 'success'
            });
            // this.$router.push('/login')
          }
          else{
            this.$message({
              message: result.message,
              type: 'warning'
            })
          }
        })
      }
    }
  }
</script>


<style scoped>
  .text-input{
    position: relative;
    clear: both;
    width: 300px;
    height: 20px;
    outline: none;
    background: #fff;
  }
</style>
