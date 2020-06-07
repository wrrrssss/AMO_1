<template>
  <el-container>
    <el-aside width="240px">
      <el-menu>
        <el-menu-item index="1" disabled>消息中心</el-menu-item>
        <router-link to="/space/message/message" style="font-size: 18px;text-decoration: none"><el-menu-item index="2">我的消息</el-menu-item></router-link>
        <router-link to="/space/message/chat" style="font-size: 18px;text-decoration: none"><el-menu-item index="4">我的私信</el-menu-item></router-link>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: 'Message',
    data(){
      return{

      }
    },
    mounted() {

    },
  }
</script>

<style scoped>

</style>
