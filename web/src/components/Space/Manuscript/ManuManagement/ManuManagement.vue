<template>
    <el-container>
      <el-header>
        <el-menu default-active="activeIndex" mode="horizontal" style="height: 52px;" @select="handleSelect">
          <el-menu-item index="1"><router-link to="/space/manuscript/management/manu"  style="font-size: 14px;text-decoration: none">稿件管理</router-link></el-menu-item>
          <el-menu-item index="2"><router-link to="/space/manuscript/management/video" style="font-size: 14px;text-decoration: none">视频管理</router-link></el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
</template>

<script>
  export default {
    name: 'ManuManagement',
    data(){
      return{
        activeIndex:'1',
      }
    },

    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
    }
  }
</script>

<style scoped>
.space-main{
  margin: 0;
}
</style>
