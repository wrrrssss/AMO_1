<template>
  <el-container>
    <el-aside>
      <p>他的收藏</p>
      <el-menu>
        <el-menu-item v-for="(item,i) in collections" :key="i">
          <router-link :to="'/space/'+item.user_id+'/collections/detail/'+item.id">{{item.name}}</router-link>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
    import axios from 'axios'
    export default {
      name: "OtherCollection",
      data(){
        return{
          collections:[],
        }
      },
      mounted() {
        let url =`http://localhost:8088/collect/getAllList`
        axios.get(url,{
          params:{
            user_id:JSON.parse(sessionStorage.getItem('other')).id
          }
        }).then(res=>{
          this.collections=res.data
          console.log(this.collections)
        })
      },
      methods:{

      }
    }
</script>

<style scoped>

</style>
