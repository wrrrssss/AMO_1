<template>
  <div>
    <div v-if="isEmpty">
      <h2>收藏夹为空</h2>
    </div>
    <div v-else>
      <el-row>
        <el-col :span="8" v-for="(info,i) in collectionInfo" :key="i">
          <el-card :body-style="{ padding: '0px'}">
            <div class="image">
              <el-image :src="require('../../../assets/'+info.cover+'.JPG')" fit="contain"></el-image>
            </div>
            <div style="padding: 14px;">
              <p>{{info.title}}</p>
              <time>{{info.publishdate}}</time>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
      name: "OtherCollectionDetail",
      data(){
        return{
          collectionId:'',
          collectionInfo:[],
          isEmpty:true,
        }
      },
      mounted() {
        this.collectionId=this.$route.params.id
        let url =`http://localhost:8088/collect/getAllManuscriptOfList`
        axios.get(url,{
          params:{
            list_id:this.collectionId
          }
        }).then(res=>{
          this.collectionInfo=res.data
          if(this.collectionInfo.length!=0) {
            this.isEmpty=false
          }
        })
      }
    }
</script>

<style scoped>

</style>
