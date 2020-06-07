<template>
  <el-container>
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu default-active="activeIndex">
        <el-submenu index="1">
          <template slot="title">他的关注</template>
          <el-menu-item-group>
            <el-menu-item v-for="(item,i) in groups" :key="i">
              <router-link :to="'/space/'+userId+'/friend/focus/'+item.id" style="text-decoration: none">
                {{item.name}}
              </router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="2">
          <router-link :to="'/space/'+userId+'/friend/fans'" style="text-decoration: none">他的粉丝</router-link>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
    import axios from "axios";
    export default {
      name: "OtherFriend",
      data(){
        return{
          groups:[],
          userId:'',
        }
      },
      mounted() {
        this.userId=JSON.parse(sessionStorage.getItem('other')).id
        let url1 = `http://localhost:8088/friend/getAllGroup`
        axios.get(url1,{
          params:{
            user_id:this.userId
          }
        }).then(res=>{
          this.groups=res.data
        })
      },
    }
</script>

<style scoped>

</style>
