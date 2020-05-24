<template>
  <div class="lee-picture-card">
    <img :src="imgURL" class="IMG">
    <div class="card-info" style="border-bottom:0 solid gainsboro;height:20px">
      <span class="large-text">&nbsp;&nbsp;{{ manuscript.title }}</span>
      <!--<el-button class="addBtn" size="mini" @click="addTag" circle style="float: right; margin-right: 4px" type="primary" icon="el-icon-edit"></el-button>-->
      <el-button class="moreBtn" size="mini" @click.native="moreMessage" circle style="float: right;  margin-right:4px" type="success" icon="el-icon-message"></el-button>
    </div>

    <div class="view">
      <h5>点赞数: {{this.manuscript.love}} 收藏数：{{this.manuscript.collection}} 转发数: {{this.manuscript.forward}} 评论数: {{this.manuscript.comment}}</h5>
    </div>
  </div>

</template>

<script>
  import axios from 'axios'
  export default {
    name: 'pictureCard',
    data() {
      return {
        imgURL: '',
        tags: [],
        viewedTime: ''
      }
    },
    props: {
      manuscript: {
        type: Object,
        default () {
          return {}
        }
      }

    },
    created() {
      this.imgURL = require('@/assets/' + this.manuscript.cover+'.JPG');
      // this.getViewedTime();
      console.log(this.manuscript.comment)
    },
    methods: {
      moreMessage(){
        this.$router.push('/article/'+this.manuscript.id)
      },
    }
  }
</script>

<style scope>
  .IMG{
    width: 100%;
    height: 150px;
    border-radius: 5px;
  }

  .lee-picture-card{
    position: relative;
    background-color: #f3f3f3;
    margin-top: 2rem;
    border-radius: .4rem;
    height: 240px;
  }

  .large-text{
    font-size: 1.2rem;
  }

  .description{
    margin-top: 0rem;
    font-size: 1.0rem;

    color: gray;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    height: 20px;
  }

  .card-footer {
    padding: 0rem;
    text-align: left;
  }
  .left {
    color: grey;
    font-size: 1.0rem;
    text-align: center;
  }
  .tag-card{
    margin-top: 5px;
  }
  .view{
    margin-top: 5px;
    float: right;
  }


</style>
