<template>
  <el-container>
    <el-header>
    <div class="user-list">
      <el-menu default-active="activeIndex" mode="horizontal" style="height: 52px" @select="handleSelect">
        <el-menu-item index="1"><router-link to="/space/message"  style="font-size: 18px;text-decoration: none">消息</router-link></el-menu-item>
        <el-menu-item index="2"><router-link to="/space/dynamics" style="font-size: 18px;text-decoration: none">动态</router-link></el-menu-item>
        <el-menu-item index="3"><router-link to="/space/collections"style="font-size: 18px;text-decoration: none">收藏</router-link></el-menu-item>
        <el-menu-item index="4"><router-link to="/space/manuscript"style="font-size: 18px;text-decoration: none">投稿</router-link></el-menu-item>
        <el-menu-item index="5"><router-link to="/space/friend"style="font-size: 18px;text-decoration: none">好友</router-link></el-menu-item>
        <el-menu-item index="6"><router-link to="/space/history"style="font-size: 18px;text-decoration: none">历史</router-link></el-menu-item>
        <el-menu-item index="7"><router-link to="/space/edit"style="font-size: 18px;text-decoration: none">编辑信息</router-link></el-menu-item>
      </el-menu>
    </div>
    <div class="user-log">
      <el-menu mode="horizontal" text-color="#1C86EE" active-text-color="#000080" style="height: 52px; margin: 0 auto;border: 0;float: right">
        <el-menu-item>
          <router-link to="/space" target='_blank' style="font-size: 18px;text-decoration: none">
            <el-avatar v-if="avatar" :src="require('../../assets/'+avatar+'.JPG')"></el-avatar>
            <el-avatar v-else icon="el-icon-user-solid"></el-avatar>
            <span>{{username}}</span>
          </router-link>
        </el-menu-item>
      </el-menu>
    </div>
    </el-header>
  </el-container>
</template>

<script>
  export default {
    name: 'SpaceHead',
    data() {
      //这是导航栏
      return {
        activeIndex: '',
        avatar:'',
        username:'',
      };
    },
    mounted () {
      if(this.$route.params)
        this.activeIndex=this.$route.params
      this.avatar=JSON.parse(sessionStorage.getItem('form')).picture
      this.username=JSON.parse(sessionStorage.getItem('form')).username
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
    }
  }
</script>

<style scoped>
  .user-list{
    float: left;
    height: 100%;
  }
  .user-log{
    float: right;
    height: 100%;
  }
</style>
