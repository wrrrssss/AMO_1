<template>
  <div id=register_form>
    <el-form ref="form" :rules="rules" :model="form" label-width="80px" label-position="right">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="确认或修改密码" prop="password">
        <el-input type="password" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="再次输入" prop="password2">
        <el-input type="password" v-model="form.password2"></el-input>
      </el-form-item>
      <el-form-item label="上传头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8088/upload"
          :show-file-list="false"
          :on-change="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="this.imgUrl" :src="require('../../../assets/'+this.imgUrl.substring(0,this.imgUrl.length-4)+'.JPG')" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改完成</el-button>
        <router-link to="/login"><el-button>登录</el-button></router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {hex_md5} from "../../../assets/MD5";
  export default {
    name: 'Edit',
    data() {
      return {
        form: {
          name: '',
          password: '',
          password2:'',
          picture:'',
        },
        info:{
          id:'',
          username:'',
          password:'',
          picture: '',
        },
        imgUrl:'',
        rules: {
          name: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 4, max: 20, message: '长度在 4 到 20 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
          ],
          password2:[
            {required: true,message:'请再次输入密码',trigger: 'blur'}
          ],
        }
      }
    },
    mounted() {
      this.info.id=JSON.parse(sessionStorage.getItem('form')).id
      this.form.name=JSON.parse(sessionStorage.getItem('form')).username
      this.form.picture=JSON.parse(sessionStorage.getItem('form')).picture
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.form.password!=this.form.password2){
              alert('两次密码不一致！');
            }
            else{
              this.info.username=this.form.name
              this.info.password=hex_md5(this.form.password)
              let url = `http://localhost:8088/user/update`
              //console.log("我在这")
              console.log(this.info)
              axios.post(url,this.info).then((res=>{
                //console.log(res)
                if(res.data.success=true){
                  alert('修改成功')
                  window.sessionStorage.removeItem('form')
                  this.$router.push('/login')
                }
              }))
            }
          } else {
            alert('请正确输入！');
            return false;
          }
        });

      },
      handleAvatarSuccess(res) {
        console.log(res)
        this.imgUrl=res
        this.info.picture=res
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    },
  }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
