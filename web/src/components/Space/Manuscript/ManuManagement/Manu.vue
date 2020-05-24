<template>
  <el-container>
  <el-main>
    <div v-for="(item,i) in manudata" :key="i" class="s-content">
      <a :href="`http://localhost:8080/#/article/`+item.id">
       <el-image :src="require('../../../../assets/'+item.cover+'.JPG')"
                  style="width: 100px; height: 100px;float: left"
                  fit="contain"
                  :lazy="true">
        </el-image>
      </a>
      <h2 style="text-align: left">{{item.title}}</h2>
      <div class="manu-data">
        <span><i class="el-icon-thumb"></i>{{item.love}}</span>
        <span><i class="el-icon-chat-line-round"></i>{{item.comment}}</span>
        <span><i class="el-icon-link"></i>{{item.forward}}</span>
        <span><i class="el-icon-collection"></i>{{item.collection}}</span>
      </div>
    </div>
  </el-main>
    <el-footer>
      <el-pagination small
                 layout="prev, pager, next"
                 :total="50"
                 @current-change="handleCurrentChange"
                 :current-page.sync="currentPage1">
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Manu',
    data(){
      return{
        manudata:[],
        currentPage1:1,
      }
    },
    mounted () {
      let userId = JSON.parse(sessionStorage.getItem('form')).id
      let url =`http://localhost:8088/recommend/getManuscriptByUser/`
      axios.get(url,{
        params:{
          user_id:userId
        }
      }).then(res=>{
        this.manudata=res.data
        console.log(this.manudata)
      })
    },
    methods:{
      handleCurrentChange(val){
        let userId = JSON.parse(sessionStorage.getItem('form')).id
        let url =`http://localhost:8088/manuscript/list/${this.currentPage1}/${userId}`
        axios.post(url).then(res=>{
          this.manudata=res.data.list
        })
      },
    }
  }
</script>

<style scoped>
  .s-content{
    width: 720px;
    height: 120px;
  }
  .s-content h2{
    max-height: 26px;
    font-size: 18px;
    color: #222;
    line-height: 26px;
    overflow: hidden;
    display: block;
  }
  .manu-data{
    margin-top: 15px;
    color: #999;
    font-size: 0;
  }
  .manu-data span{
    display: inline-block;
    font-size: 14px;
    overflow: hidden;
    margin-right: 20px;
    height: 14px;
    line-height: 16px;
    white-space: nowrap;
    float: left;
  }

</style>
