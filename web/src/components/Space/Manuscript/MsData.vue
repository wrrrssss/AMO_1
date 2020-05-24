<template>
  <div>
    <el-container style="border: 1px solid #eee; padding: 0;margin: 0" >
      <el-header>
        稿件数据
      </el-header>
    <el-main>
      <div class="data-card">
        <h4>粉丝数</h4>
        <p>{{msData.numOfFans}}</p>
      </div>
      <div class="data-card">
        <h4>点赞数</h4>
        <p>{{msData.numOfLikes}}</p>
      </div>
      <div class="data-card">
        <h4>评论数</h4>
        <p>{{msData.numOfReviews}}</p>
      </div>
      <div class="data-card">
        <h4>分享数</h4>
        <p>{{msData.numOfShares}}</p>
      </div>
      <div class="data-card">
        <h4>收藏数</h4>
        <p>{{msData.numOfCollections}}</p>
      </div>
      </el-main>
      <el-header>
        数据统计
      </el-header>
      <el-main>

      </el-main>
    </el-container>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'MsData',
    data(){
      return{
        msData:{
          numOfFans:0,
          numOfLikes:0,
          numOfReviews:0,
          numOfShares:0,
          numOfCollections:0,
        },
        currentPage1:'1',
      }
    },
    mounted() {
      let userId = JSON.parse(sessionStorage.getItem('form')).id
      let url =`http://localhost:8088/recommend/getAllManuscript/`
      axios.get(url,{
        params:{
          user_id:userId
        }
      }).then(res=>{
        let manudata=res.data
        console.log(manudata)
        for(let i=0;i<manudata.length;i++){
          this.msData.numOfLikes=this.msData.numOfLikes+manudata[i].love
          this.msData.numOfReviews=this.msData.numOfReviews+manudata[i].comment
          this.msData.numOfShares=this.msData.numOfShares+manudata[i].forward
          this.msData.numOfCollections=this.msData.numOfCollections+manudata[i].collection
        }
      })
      let url1 =`http://localhost:8088/friend/getFansNumber`
      axios.get(url1,{
        params:{
          user_id:userId
        }
      }).then(res=>{
        this.msData.numOfFans=res.data
      })
    },
  }
</script>

<style scoped>
  .data-card{
    width: 33%;
    height: 100px;
    flex: 0 0 auto;
    float: left;
  }
  .el-header{
    font-size: 16px;
    text-align: left;
    background-color: #B3C0D1;
    line-height: 60px;
  }
</style>
