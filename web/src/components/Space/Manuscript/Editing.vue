<template>
  <div class="wrap">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="封面" prop="cover">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8088/upload/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="form.cover" :src="require('../../../assets/'+form.cover+'.JPG')" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <quill-editor
          v-model="form.content"
          :options="editorOption"
        >
        </quill-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  import {makeDate} from "../../../assets/dateformat";

  export default {
    name: 'Editing',
    data(){
      return {
        form: {
          userId:'',
          username:'',
          title: '',
          cover:'',
          content:  '', // 富文本编辑器默认内容
          publishdate:'',
        },
        editorOption: {
          theme: 'snow',
          placeholder: '请输入正文'
        },
        rules: {
            title: [
              {required: true, message: '请输入标题', trigger: 'blur'},
            ],
            cover: [
              {required: true, message: '请选择封面', trigger: 'blur'},
            ],
            content:[
              {required: true, message: '请输入正文', trigger: 'blur'},
            ]
        }
      }
    },
    computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
    },
    created () {
      this.editor = this.$refs.myQuillEditor.quill;
    },
    methods: {
      onSubmit (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form.userId=JSON.parse(sessionStorage.getItem('form')).id
            this.form.username=JSON.parse(sessionStorage.getItem('form')).username
            this.form.publishdate=makeDate(new Date())
            let url = `http://localhost:8088/manuscript/publish`;
            axios.post(url,this.form).then(res => {
              console.log(res)
              if(res.data.success==true)
                alert('发布成功')
            })
          } else {
            alert('输入不能为空！');
          }
        });

      },
      handleAvatarSuccess (res) {
        this.form.cover = res
        this.form.cover = this.form.cover.substring(0,this.form.cover.length-4)
      },
      beforeAvatarUpload (file) {
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
    }
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
    width: 300px;
    height: 190px;
    line-height: 190px;
    text-align: center;
  }
  .avatar {
    width: 300px;
    height: 190px;
    display: block;
  }
</style>
