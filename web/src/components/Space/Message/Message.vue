<template>
  <el-container>
    <el-aside width="240px">
      <el-menu>
        <el-menu-item index="1" disabled>消息中心</el-menu-item>
        <router-link to="/space/message/reply" style="font-size: 18px;text-decoration: none"><el-menu-item index="2">回复我的</el-menu-item></router-link>
        <router-link to="/space/message/thumps" style="font-size: 18px;text-decoration: none"><el-menu-item index="3">收到的赞</el-menu-item></router-link>
        <router-link to="/space/message/mymessage" style="font-size: 18px;text-decoration: none"><el-menu-item index="4">我的消息</el-menu-item></router-link>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: 'Message'
  }
</script>

<style scoped>

</style>
