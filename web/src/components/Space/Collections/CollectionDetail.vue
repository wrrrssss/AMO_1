<template>
  <div>
    <div v-if="isEmpty">
      <h2>收藏夹为空</h2>
    </div>
    <div v-else>
      <el-row>
        <el-col :span="6" v-for="(info,i) in collectionInfo" :key="i">
          <el-card :body-style="{ padding: '0px'}">
            <div class="image">
              <el-image :src="require('../../../assets/'+info.cover+'.JPG')" fit="contain"></el-image>
            </div>
            <div style="padding: 14px;">
              <p>{{info.title}}</p>
              <time>{{info.publishdate}}</time>
              <el-dropdown placement="bottom-start">
                <span class="el-dropdown-link"><i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="noCollection(i)">取消收藏</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
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
    name: 'CollectionDetail',
    data(){
      return{
        collectionId:'',
        collectionInfo:[],
        dialogVisible:false,
        isEmpty:true,
      }
    },
    mounted () {
      this.collectionId=this.$route.params.id
      let url =`http://localhost:8088/collect/getAllManuscriptOfList`
      axios.get(url,{
        params:{
          list_id:this.collectionId
        }
      }).then(res=>{
        this.collectionInfo=res.data
        if(this.collectionInfo.length!=0)
          this.isEmpty=false
      })
    },
    methods:{
      noCollection(index){
        let tmp ={
          userId:JSON.parse(sessionStorage.getItem('form')).id,
          manuscriptId:this.collectionInfo[index].id,
        }
        let url1 = `http://localhost:8088/manuscript/cancelCollection`
        axios.post(url1,tmp)
        let url =`http://localhost:8088/collect/moveOutList`
        axios.get(url,{
          params:{
            manuscript_id:tmp.manuscriptId,
            user_id:tmp.userId
          }
        }).then(res=>{
          if(res.data.success==true)
            this.$router.go(0)
        })
      },
    }
  }
</script>

<style scoped>

</style>
