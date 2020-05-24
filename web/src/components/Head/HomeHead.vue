<template>
  <el-container>
    <el-header>
    <div class="hp-header">
          <div class="nav-amo">
            <p>AMO</p>
          </div>
          <div class="nav-search-box">
            <el-form :inline="true">
              <router-link to="/search"><el-button>搜索</el-button></router-link>
              <router-link to="/recommend"> <el-button>推荐内容</el-button></router-link>
            </el-form>
          </div>
          <div class="nav-user-center">
              <div class="head-image"></div>
              <div class="user-list">
                <el-menu mode="horizontal" style="height: 52px; margin: 0 auto;border: 0">
                  <el-menu-item><router-link to="/space/message" target='_blank' style="font-size: 18px;text-decoration: none">消息</router-link></el-menu-item>
                  <el-menu-item><router-link to="/space/dynamics" target='_blank' style="font-size: 18px;text-decoration: none">动态</router-link></el-menu-item>
                  <el-menu-item><router-link to="/space/collections" target='_blank' style="font-size: 18px;text-decoration: none">收藏</router-link></el-menu-item>
                  <el-menu-item><router-link to="/space/manuscript" target='_blank' style="font-size: 18px;text-decoration: none">投稿</router-link></el-menu-item>
                </el-menu>
              </div>
              <div class="user-log">
                <el-menu mode="horizontal" text-color="#1C86EE" active-text-color="#000080" style="height: 52px; margin: 0 auto;border: 0;float: right" v-if="isLog">
                  <el-menu-item>
                    <router-link to="/space" target='_blank' style="font-size: 18px;text-decoration: none">
                      <el-avatar v-if="avatar" :src="require('../../assets/'+avatar+'.JPG')"></el-avatar>
                      <el-avatar v-else icon="el-icon-user-solid"></el-avatar>
                      <span>{{username}}</span>
                    </router-link>
                  </el-menu-item>
                </el-menu>
                <el-menu mode="horizontal" text-color="#1C86EE" active-text-color="#000080" style="height: 52px; margin: 0 auto;border: 0;float: right" v-else="isLog">
                  <el-menu-item><router-link to="/login" target='_blank' style="font-size: 18px;text-decoration: none">登录</router-link></el-menu-item>
                  <el-menu-item><router-link to="/register" target='_blank' style="font-size: 18px;text-decoration: none">注册</router-link></el-menu-item>
                </el-menu>
              </div>
        </div>
      <div class="amo-banner"></div>
    </div>
    </el-header>
  </el-container>
</template>

<script>
  export default {
    name: 'HomeHead',
    data(){
      return{
        input:'',
        activeIndex:'',
        isLog:false,
        avatar:'',
        username:'',
        select:'',
        content:'',
      }
    },
    created() {
      if(JSON.parse(sessionStorage.getItem('form'))!=null){
        this.isLog=true
        this.avatar =JSON.parse(sessionStorage.getItem('form')).picture
        this.username=JSON.parse(sessionStorage.getItem('form')).username
      }
    },
    methods:{
      search(){
        if(this.select!=null||this.content!=null){
          console.log(this.select)
          console.log(this.content)
          this.$router.push({
            path:'/search',
            params:{
              select:this.select,
              content:this.content
            }
          })
        }
        else {
          this.$router.push('/search')
        }
      }
    }
  }
</script>

<style scoped>
  .homepage{
    margin:0;
    padding: 0;
  }
  .hp-header{
    z-index: 1;
    width: 100%;
    height: 56px;
  }
  .nav-amo{
    float: left;
    height: 56px;
  }
  .nav-search-box{
    margin: 0 10px;
    width: 500px;
    transition: width .3s;
    float: left;
  }
  .nav-user-center{
    float: left;
  }
  .user-con signin{
    float: left;
  }
  .head-image{
    float: left;
  }
  .user-list{
    float: left;
    height: 100%;
  }
  .user-log{
    float: right;
    height: 100%;
  }


</style>
