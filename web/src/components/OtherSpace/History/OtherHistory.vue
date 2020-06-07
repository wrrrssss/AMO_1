<template>
  <div>
    <el-container v-for="(item,i) in history" :key="i">
      <el-header>
        <span>{{item.check_time}}</span>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <a :href="'http://localhost:8080/#/article/'+manuInfo[i].id">
            <el-image :src="require('../../../assets/'+manuInfo[i].cover+'.JPG')" fit="fill">
            </el-image>
          </a>
        </el-aside>
        <el-main>
          <h2>{{manuInfo[i].username}}</h2>
          <div class="manu-data">
            <span><i class="el-icon-thumb"></i>{{manuInfo[i].love}}</span>
            <span><i class="el-icon-chat-line-round"></i>{{manuInfo[i].comment}}</span>
            <span><i class="el-icon-link"></i>{{manuInfo[i].forward}}</span>
            <span><i class="el-icon-collection"></i>{{manuInfo[i].collection}}</span>

            <el-dropdown placement="bottom-start">
              <span><i class="el-icon-arrow-down el-icon--right"></i></span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="deleteHistory(item.id)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "OtherHistory",
    data(){
      return{
        history:[],
        manuInfo:[],
      }
    },
    mounted() {
      let url =`http://localhost:8088/history/getAllHistoryOfUser`
      axios.get(url,{
        params:{
          user_id:JSON.parse(sessionStorage.getItem('other')).id
        }
      }).then(res=>{
        this.history=res.data
        console.log(this.history.length)
        for(let i=0;i<this.history.length;i++){
          this.getManuData(this.history[i].manuscript_id)
        }
        console.log(this.history)
        console.log(this.manuInfo)
      })
    },
    methods:{
      getManuData(id){
        /*传稿件id获取稿件数据的方法*/
        let url = `http://localhost:8088/manuscript/selById/${id}`
        axios.get(url).then(res=>{
          this.manuInfo.push(res.data)
        })
      },
      deleteHistory(id){
        let url = `http://localhost:8088/history/deleteHistory`
        axios.get(url,{
          params:{
            id:id,
          }
        }).then(res=>{
          if(res.data.success==true){
            alert('删除成功')
            this.$router.go(0)
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>
